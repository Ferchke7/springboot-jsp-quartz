package com.example.hometaskfc.controller;

import com.example.hometaskfc.dto.ScheduleEmailDto;
import com.example.hometaskfc.models.EmailRequestModel;
import com.example.hometaskfc.services.EmailJob;
import com.example.hometaskfc.services.EmployeeService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Controller
public class EmailController {
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private Scheduler scheduler;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emailscheduler")
    public String emailScheduler() {
        return "emailscheduler";
    }


    @PostMapping("/scheduler")
    public String emailSchedule(@ModelAttribute ScheduleEmailDto scheduleEmailDto) throws SchedulerException {

            StringBuilder stringBuilder = employeeService.getListForEmail();

            ZonedDateTime dateTime = ZonedDateTime.of(scheduleEmailDto.getDateTime(),scheduleEmailDto.getTimeZone());
            if(dateTime.isBefore(ZonedDateTime.now())) {
                return "exceptionPages/failpage";
            }
            else {
                EmailRequestModel emailRequestModel = new EmailRequestModel();
                emailRequestModel.setEmail(scheduleEmailDto.getEmail());
                emailRequestModel.setDateTime(scheduleEmailDto.getDateTime());
                emailRequestModel.setTimeZone(scheduleEmailDto.getTimeZone());
                emailRequestModel.setBody(stringBuilder.toString());
                emailRequestModel.setSubject("Employee Lists");
                JobDetail jobDetail = buildJobDetail(emailRequestModel);
                Trigger trigger = buildJobTrigger(jobDetail,dateTime);
                scheduler.scheduleJob(jobDetail,trigger);
                return "emailscheduler";
            }

    }

    private JobDetail buildJobDetail(EmailRequestModel requestModel) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("email", requestModel.getEmail());
        jobDataMap.put("subject", requestModel.getSubject());
        jobDataMap.put("body", requestModel.getBody());

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt){
        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}

package com.example.hometaskfc.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ScheduleEmailDto {
    private String email;
    private LocalDateTime dateTime;

    private ZoneId timeZone;

    public ScheduleEmailDto() {
    }

    public ScheduleEmailDto(String email, LocalDateTime dateTime, ZoneId timeZone) {
        this.email = email;
        this.dateTime = dateTime;
        this.timeZone = timeZone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(ZoneId timeZone) {
        this.timeZone = timeZone;
    }
}

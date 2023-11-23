package com.digitalcreators.digicreatefon.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Calendar;

@Getter
@Setter
public class SystemDateDto {

    private Date system_date;
    private java.util.Date system_date_time;

    public SystemDateDto(){

    }

    public static Date getNextDay(Date currentDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDay);
        calendar.add(Calendar.DAY_OF_MONTH, 1);  // Bir gün ekler
        return new Date(calendar.getTimeInMillis());
    }

    public SystemDateDto(Date date){
        setSystem_date(date);
        setSystem_date_time(null);
    }
}

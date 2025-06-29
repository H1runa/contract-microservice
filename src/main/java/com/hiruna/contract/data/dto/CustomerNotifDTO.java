package com.hiruna.contract.data.dto;

import java.sql.Time;
import java.util.Date;

public class CustomerNotifDTO {
    private String title;
    private int customer_id;
    private String description;
    private Date date;
    private Time time;


    public CustomerNotifDTO() {
    }

    public CustomerNotifDTO(int customer_id, String title, String description, Date date, Time time) {
        this.customer_id = customer_id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}

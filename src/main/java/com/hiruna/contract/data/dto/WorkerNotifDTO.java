package com.hiruna.contract.data.dto;

import java.sql.Date;
import java.sql.Time;

public class WorkerNotifDTO {
    private int worker_id;
    private String title;
    private String description;
    private Date date;
    private Time time;

    public WorkerNotifDTO() {
    }

    public WorkerNotifDTO(int worker_id, String title, String description, Date date, Time time) {
        this.worker_id = worker_id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

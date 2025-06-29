package com.hiruna.contract.data.dto;

import java.sql.Time;
import java.util.Date;

public class CustomerNotifDTO {
    private Integer id;
    private int customer_id;
    private int worker_id;
    private int customer_contract_id;
    private int worker_contract_id;
    private String title;
    private String description;
    private Date date;
    private Time time;
    private String status;


    public CustomerNotifDTO() {
    }

    public CustomerNotifDTO(Integer id, int customer_id, int worker_id, int customer_contract_id, int worker_contract_id, String title, String description, Date date, Time time, String status) {
        this.id = id;
        this.customer_id = customer_id;
        this.worker_id = worker_id;
        this.customer_contract_id = customer_contract_id;
        this.worker_contract_id = worker_contract_id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public int getCustomer_contract_id() {
        return customer_contract_id;
    }

    public void setCustomer_contract_id(int customer_contract_id) {
        this.customer_contract_id = customer_contract_id;
    }

    public int getWorker_contract_id() {
        return worker_contract_id;
    }

    public void setWorker_contract_id(int worker_contract_id) {
        this.worker_contract_id = worker_contract_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.hiruna.contract.data;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_contract")
public class CustomerContract {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "addr_line_1")
    private String addr_line_1;

    @Column(name = "addr_line_2")
    private String addr_line_2;

    @Column(name = "addr_line_3")
    private String addr_line_3;

    @Column(name = "service_id")
    private int service_id;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "request_status")
    private String request_status;

    public CustomerContract() {
    }

    public CustomerContract(int id, String title, String description, String addr_line_1, String addr_line_2, String addr_line_3, int service_id, int customer_id, String request_status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.addr_line_1 = addr_line_1;
        this.addr_line_2 = addr_line_2;
        this.addr_line_3 = addr_line_3;
        this.service_id = service_id;
        this.customer_id = customer_id;
        this.request_status = request_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddr_line_1() {
        return addr_line_1;
    }

    public void setAddr_line_1(String addr_line_1) {
        this.addr_line_1 = addr_line_1;
    }

    public String getAddr_line_2() {
        return addr_line_2;
    }

    public void setAddr_line_2(String addr_line_2) {
        this.addr_line_2 = addr_line_2;
    }

    public String getAddr_line_3() {
        return addr_line_3;
    }

    public void setAddr_line_3(String addr_line_3) {
        this.addr_line_3 = addr_line_3;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getRequest_status() {
        return request_status;
    }

    public void setRequest_status(String request_status) {
        this.request_status = request_status;
    }
}

package com.hiruna.contract.data;

import jakarta.persistence.*;

@Entity
@Table(name = "worker_contract")
public class WorkerContract {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cust_contract_id")
    private int cust_contract_id;

    @Column(name = "worker_id")
    private int worker_id;

    @Column(name = "job_status")
    private String job_status;

    public WorkerContract() {
    }

    public WorkerContract(int id, int cust_contract_id, int worker_id, String job_status) {
        this.id = id;
        this.cust_contract_id = cust_contract_id;
        this.worker_id = worker_id;
        this.job_status = job_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCust_contract_id() {
        return cust_contract_id;
    }

    public void setCust_contract_id(int cust_contract_id) {
        this.cust_contract_id = cust_contract_id;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public String getJob_status() {
        return job_status;
    }

    public void setJob_status(String job_status) {
        this.job_status = job_status;
    }
}

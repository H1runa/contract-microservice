package com.hiruna.contract.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerContractRepository extends JpaRepository<WorkerContract, Integer> {
    @Query("select wc from WorkerContract wc where wc.cust_contract_id = ?1 AND wc.job_status = ?2")
    public List<WorkerContract> getWContractsByCusContractIdAndStatus(int id, String status);

    @Query("select wc from WorkerContract wc where wc.worker_id = ?1")
    public List<WorkerContract> getWContractsByWorkerId(int id);

    @Query("select wc from WorkerContract wc where wc.job_status = ?1")
    public List<WorkerContract> getWContractByStatus(String status);
}

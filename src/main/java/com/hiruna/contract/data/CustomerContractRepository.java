package com.hiruna.contract.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerContractRepository extends JpaRepository<CustomerContract, Integer> {
    @Query("select cc from CustomerContract cc where cc.title LIKE %?1%")
    public List<CustomerContract> findByTitle(String title);

    @Query("select cc from CustomerContract cc where cc.description LIKE %?1%")
    public List<CustomerContract> findByDesc(String desc);

    @Query("select cc from CustomerContract cc where cc.service_id = ?1 AND cc.request_status='Pending' ")
    public List<CustomerContract> findByServID(int id);

    @Query("select cc from CustomerContract cc where cc.customer_id = ?1")
    public List<CustomerContract> findByCusId(int id);

    @Query("select cc from CustomerContract cc where cc.request_status = ?1")
    public List<CustomerContract> findByStatus(String status);

    @Query("select cc from CustomerContract cc where cc.customer_id = ?1 AND cc.request_status != 'Cancelled'")
    public List<CustomerContract> findActiveContractsForCustomer(int id);


    //code added by Desan
    @Query("select cc from CustomerContract cc where (cc.description LIKE %?1% or cc.title LIKE %?1% or cc.addr_line_3 LIKE %?1%) and cc.request_status = 'Pending'")
    public List<CustomerContract> findByKeyword(String keyword);

    @Query("select cc from CustomerContract cc where cc.request_status = ?1 and cc.customer_id = ?2")
    public List<CustomerContract> getByStatus(String status,int id);
}

package com.hiruna.contract.service;

import com.hiruna.contract.data.CustomerContract;
import com.hiruna.contract.data.CustomerContractRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerContractService {
    private CustomerContractRepository cusContractRepo;

    public CustomerContractService(CustomerContractRepository cusContractRepo){
        this.cusContractRepo = cusContractRepo;
    }

    public CustomerContract createCusContract(CustomerContract cc){
        return cusContractRepo.save(cc);
    }

    public CustomerContract getCusContractById(int id){
        Optional<CustomerContract> contract = cusContractRepo.findById(id);
        if (contract.isPresent()){
            return contract.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request Customer Contract was not found");
        }
    }

    public List<CustomerContract> getCusContractByTitle(String title){
        return cusContractRepo.findByTitle(title);
    }

    public List<CustomerContract> getCusContractByDesc(String desc){
        return cusContractRepo.findByDesc(desc);
    }

    public List<CustomerContract> getCusContractBySevID(int id){
        return cusContractRepo.findByServID(id);
    }

    public List<CustomerContract> getCusContractByCusID(int id){
        return cusContractRepo.findByCusId(id);
    }

    public List<CustomerContract> getCusContractByStatus(String status){
        return cusContractRepo.findByStatus(status);
    }

    public List<CustomerContract> getAllCusContracts(){
        return cusContractRepo.findAll();
    }

    public CustomerContract updateCusContract(CustomerContract contract){
        return cusContractRepo.save(contract);
    }

    public void deleteCusContract(int id){
        cusContractRepo.deleteById(id);
    }

    public Boolean cusContractExistsById(int id){
        return cusContractRepo.existsById(id);
    }
}

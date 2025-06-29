package com.hiruna.contract.service;

import com.hiruna.contract.data.CustomerContract;
import com.hiruna.contract.data.CustomerContractRepository;
import com.hiruna.contract.data.WorkerContract;
import com.hiruna.contract.data.WorkerContractRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCoordinator {
    private CustomerContractRepository customerContractRepository;
    private WorkerContractRepository workerContractRepository;

    public ServiceCoordinator(CustomerContractRepository customerContractRepository, WorkerContractRepository workerContractRepository){
        this.customerContractRepository =customerContractRepository;
        this.workerContractRepository=workerContractRepository;
    }

    //customer contract services
    public Boolean cusContractExistsById(int id){
        return customerContractRepository.existsById(id);
    }

    public CustomerContract getCusContractById(int id){
        Optional<CustomerContract> contract = customerContractRepository.findById(id);
        if (contract.isPresent()){
            return contract.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request Customer Contract was not found");
        }
    }

    public CustomerContract updateCusContract(CustomerContract contract){
        return customerContractRepository.save(contract);
    }

    //worker contract services
    public List<WorkerContract> getWContractsByCusContractId(int id){
        return workerContractRepository.getWContractsByCusContractId(id);
    }

    public WorkerContract updateWContract(WorkerContract contract){
        return workerContractRepository.save(contract);
    }
}

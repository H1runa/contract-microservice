package com.hiruna.contract.service;

import com.hiruna.contract.data.WorkerContract;
import com.hiruna.contract.data.WorkerContractRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerContractService {
    private WorkerContractRepository workerContractRepository;
    private CustomerContractService customerContractService;

    public WorkerContractService(WorkerContractRepository workerContractRepository, CustomerContractService customerContractService){
        this.workerContractRepository = workerContractRepository;
        this.customerContractService = customerContractService;
    }

    public WorkerContract createWContract(WorkerContract contract){
        if (customerContractService.cusContractExistsById(contract.getCust_contract_id())){
            return workerContractRepository.save(contract);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Contract not found");
        }

    }

    public List<WorkerContract> getAllWContracts(){
        return workerContractRepository.findAll();
    }

    public WorkerContract getWContractById(int id){
        Optional<WorkerContract> contract = workerContractRepository.findById(id);
        if (contract.isPresent()){
            return contract.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker contract was not found for the given ID");
        }
    }

    public List<WorkerContract> getWContractsByCusContractId(int id){
        return workerContractRepository.getWContractsByCusContractId(id);
    }

    public List<WorkerContract> getWContractsByWorkerId(int id){
        return workerContractRepository.getWContractsByWorkerId(id);
    }

    public List<WorkerContract> getWContractsByStatus(String status){
        return workerContractRepository.getWContractByStatus(status);
    }

    public WorkerContract updateWContract(WorkerContract contract){
        return workerContractRepository.save(contract);
    }

    public void deleteWContract(int id){
        workerContractRepository.deleteById(id);
    }

    public Boolean workerContractExistsById(int id){
        return workerContractRepository.existsById(id);
    }
}

package com.hiruna.contract.service;

import com.hiruna.contract.data.CustomerContract;
import com.hiruna.contract.data.CustomerContractRepository;
import com.hiruna.contract.data.WorkerContract;
import com.hiruna.contract.data.dto.WorkerNotifDTO;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerContractService {
    private CustomerContractRepository cusContractRepo;
    private ServiceCoordinator serviceCoord;
    private KafkaTemplate<String, WorkerNotifDTO> workerNotifKafkaTemplate;

    public CustomerContractService(CustomerContractRepository cusContractRepo, ServiceCoordinator serviceCoord, KafkaTemplate<String, WorkerNotifDTO> workerNotifKafkaTemplate){
        this.cusContractRepo = cusContractRepo;
        this.serviceCoord=serviceCoord;
        this.workerNotifKafkaTemplate=workerNotifKafkaTemplate;
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

    public CustomerContract cancelContract(int id, String status){
        Optional<CustomerContract> contract = cusContractRepo.findById(id);
        //checking for existence
        if (contract.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer contract not found");
        }

        //updating status
        CustomerContract cus_contract = contract.get();
        cus_contract.setRequest_status("Cancelled");
        cusContractRepo.save(cus_contract);

        //updating worker contracts if there is nay
        List<WorkerContract> worker_contracts = serviceCoord.getWContractsByCusContractId(cus_contract.getId());
        if (!worker_contracts.isEmpty()){
            for (WorkerContract c : worker_contracts){
                c.setJob_status("Cancelled");
                serviceCoord.updateWContract(c);

                //sending the kafka notification message
                String formatted_string = String.format("\"%s\" contract has been cancelled by the customer.", cus_contract.getTitle());
                WorkerNotifDTO notif = new WorkerNotifDTO(null, c.getWorker_id(), cus_contract.getCustomer_id(), c.getId(), cus_contract.getId(), "Contract cancelled", formatted_string, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), "Unread");
                workerNotifKafkaTemplate.send("worker-notification", "ContractCancelled", notif);
            }
        }

        return cus_contract;
    }
}

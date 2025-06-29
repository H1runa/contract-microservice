package com.hiruna.contract.service;

import com.hiruna.contract.data.CustomerContract;
import com.hiruna.contract.data.WorkerContract;
import com.hiruna.contract.data.WorkerContractRepository;
import com.hiruna.contract.data.dto.CustomerNotifDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WorkerContractService {
    private WorkerContractRepository workerContractRepository;
    private CustomerContractService customerContractService;
    private KafkaTemplate<String, CustomerNotifDTO> customerNotifKafkaTemplate;

    public WorkerContractService(WorkerContractRepository workerContractRepository, CustomerContractService customerContractService, KafkaTemplate<String, CustomerNotifDTO> customerNotifKafkaTemplate){
        this.workerContractRepository = workerContractRepository;
        this.customerContractService = customerContractService;
        this.customerNotifKafkaTemplate = customerNotifKafkaTemplate;
    }

    public WorkerContract createWContract(WorkerContract contract){
        if (customerContractService.cusContractExistsById(contract.getCust_contract_id())){
            CustomerContract cus_contract = customerContractService.getCusContractById(contract.getCust_contract_id());
            WorkerContract c = workerContractRepository.save(contract); //creating the worker contract
            cus_contract.setRequest_status("Accepted");
            customerContractService.updateCusContract(cus_contract); //updating to the cus contract status

            //sending notif message to kafka server
            String formatted_string = String.format("ID: %d\nTitle: %s\nDescription: %s\nStatus: %s", cus_contract.getId(), cus_contract.getTitle(), cus_contract.getDescription(), cus_contract.getRequest_status());
            CustomerNotifDTO notif = new CustomerNotifDTO(cus_contract.getCustomer_id(),"Your contract has been accepted", formatted_string, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()));
            customerNotifKafkaTemplate.send("customer-notification", "ContractAccepted", notif);

            return c;
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

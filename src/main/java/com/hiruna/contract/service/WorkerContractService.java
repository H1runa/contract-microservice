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
    private ServiceCoordinator serviceCoord;
    private KafkaTemplate<String, CustomerNotifDTO> customerNotifKafkaTemplate;

    public WorkerContractService(WorkerContractRepository workerContractRepository, ServiceCoordinator serviceCoord, KafkaTemplate<String, CustomerNotifDTO> customerNotifKafkaTemplate){
        this.workerContractRepository = workerContractRepository;
        this.serviceCoord=serviceCoord;
        this.customerNotifKafkaTemplate = customerNotifKafkaTemplate;
    }

    public WorkerContract createWContract(WorkerContract contract){
        if (serviceCoord.cusContractExistsById(contract.getCust_contract_id())){
            CustomerContract cus_contract = serviceCoord.getCusContractById(contract.getCust_contract_id());
            //making sure the customer contract is still available from the server side
            if (!cus_contract.getRequest_status().equals("Pending")){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer Contract not available");
            }
            WorkerContract c = workerContractRepository.save(contract); //creating the worker contract
            cus_contract.setRequest_status("Accepted");
            serviceCoord.updateCusContract(cus_contract); //updating to the cus contract status

            //sending notif message to kafka server
            String formatted_string = String.format("Your contract, \"%s\" has been accepted by a worker", cus_contract.getTitle());
            CustomerNotifDTO notif = new CustomerNotifDTO(null, cus_contract.getCustomer_id(), c.getWorker_id(), cus_contract.getId(), c.getId(), "Your contract has been accepted", formatted_string, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), "Unread");
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

    //cancelling worker contracts
    public WorkerContract cancelWContract(int id, String status){
        Optional<WorkerContract> optional_contract = workerContractRepository.findById(id);
        //checking to see if the worker contract exists
        if (optional_contract.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker Contract not found");
        }

        WorkerContract contract = optional_contract.get();
        contract.setJob_status("Cancelled");
        WorkerContract updated_contract = workerContractRepository.save(contract);

        //updating the customer contract
        CustomerContract cus_contract = serviceCoord.getCusContractById(contract.getCust_contract_id());
        cus_contract.setRequest_status("Cancelled");
        serviceCoord.updateCusContract(cus_contract);

        //sending kafka notification message
        String formatted_string = String.format("Your contract, \"%s\" has been cancelled by the assigned worker. Contact the worker for furthur details.", cus_contract.getTitle());
        CustomerNotifDTO notif = new CustomerNotifDTO(null,  cus_contract.getCustomer_id(), updated_contract.getWorker_id(),cus_contract.getId(), updated_contract.getId(), "Your contract was cancelled", formatted_string, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),"Unread");
        customerNotifKafkaTemplate.send("customer-notification", "ContractCancelled", notif);

        return updated_contract;
    }
}

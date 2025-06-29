package com.hiruna.contract.controller;

import com.hiruna.contract.data.CustomerContract;
import com.hiruna.contract.service.CustomerContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerContractController {
    private CustomerContractService cusContractServ;

    public CustomerContractController(CustomerContractService cusContractServ){
        this.cusContractServ=cusContractServ;
    }

    //create customer contract
    @PostMapping(path = "/contracts")
    public ResponseEntity<CustomerContract> createCusContract(@RequestBody CustomerContract contract){
        CustomerContract c = cusContractServ.createCusContract(contract);
        return ResponseEntity.ok(c);
    }

    //get all customer contract
    @GetMapping(path = "/contracts")
    public ResponseEntity<List<CustomerContract>> getAllCusContracts(){
        List<CustomerContract> contracts = cusContractServ.getAllCusContracts();
        return ResponseEntity.ok(contracts);
    }

    //get customer contract by id
    @GetMapping(path = "/contracts/{id}")
    public ResponseEntity<CustomerContract> getCusContractById(@PathVariable int id){
        CustomerContract contract = cusContractServ.getCusContractById(id);
        return ResponseEntity.ok(contract);
    }

    //get customer contracts by title
    @GetMapping(path = "/contracts", params = {"title"})
    public ResponseEntity<List<CustomerContract>> getCusContractsByTitle(@RequestParam String title){
        List<CustomerContract> contracts = cusContractServ.getCusContractByTitle(title);
        return ResponseEntity.ok(contracts);
    }

    //get customer contracts by description
    @GetMapping(path = "/contracts", params = {"desc"})
    public ResponseEntity<List<CustomerContract>> getCusContractsByDesc(@RequestParam String desc){
        List<CustomerContract> contracts = cusContractServ.getCusContractByDesc(desc);
        return ResponseEntity.ok(contracts);
    }

    //get customer contracts by service id
    @GetMapping(path = "/contracts", params = {"servId"})
    public ResponseEntity<List<CustomerContract>> getCusContractsByServ(@RequestParam int servId){
        List<CustomerContract> contracts = cusContractServ.getCusContractBySevID(servId);
        return ResponseEntity.ok(contracts);
    }

    //get custoemr contracts by customer id
    @GetMapping(path = "/contracts", params = {"cusId"})
    public ResponseEntity<List<CustomerContract>> getCusContractsByCustomer(@RequestParam int cusId){
        List<CustomerContract> contracts = cusContractServ.getCusContractByCusID(cusId);
        return ResponseEntity.ok(contracts);
    }

    //get customer contracts by status
    @GetMapping(path = "/contracts", params = {"status"})
    public ResponseEntity<List<CustomerContract>> getCusContractByStatus(@RequestParam String status){
        List<CustomerContract> contracts = cusContractServ.getCusContractByStatus(status);
        return ResponseEntity.ok(contracts);
    }

    //update customer contract
    @PutMapping(path = "/contracts")
    public ResponseEntity<CustomerContract> updateCusContract(@RequestBody CustomerContract contract){
        CustomerContract cc = cusContractServ.updateCusContract(contract);
        return ResponseEntity.ok(cc);
    }

    //delete customer contract
    @DeleteMapping(path = "/contracts/{id}")
    public void deleteCusContract(@PathVariable int id){
        cusContractServ.deleteCusContract(id);
    }

    //exists endpoint
    @GetMapping(path = "/contracts/{id}/exists")
    public ResponseEntity<Boolean> cusContractExistsById(@PathVariable int id){
        Boolean exists = cusContractServ.cusContractExistsById(id);
        return ResponseEntity.ok(exists);
    }

    //cancel contract endpoint
    @PatchMapping(path = "/contracts/{id}/status")
    public ResponseEntity<CustomerContract> cancelContract(@PathVariable int id, @RequestBody Map<String, String> body){
        String status = body.get("status");
        CustomerContract contract = cusContractServ.cancelContract(id, status);
        return ResponseEntity.ok(contract);
    }
}

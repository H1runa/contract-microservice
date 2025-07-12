package com.hiruna.contract.controller;

import com.hiruna.contract.data.CustomerContract;
import com.hiruna.contract.service.CustomerContractService;
import com.hiruna.contract.service.ServiceCoordinator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerContractController {
    private CustomerContractService cusContractServ;
    private ServiceCoordinator serviceCoordinator;

    public CustomerContractController(CustomerContractService cusContractServ, ServiceCoordinator serviceCoordinator){
        this.cusContractServ=cusContractServ;
        this.serviceCoordinator=serviceCoordinator;
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

    //cancel contract endpoint
    @PatchMapping(path = "/contracts/{id}/status")
    public ResponseEntity<CustomerContract> updateCusContractStatus(@PathVariable int id, @RequestBody Map<String, String> body){
        String status = body.get("status");
        switch (status) {
            case "Cancelled":
                CustomerContract contract = cusContractServ.cancelContract(id);
                return ResponseEntity.ok(contract);

            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Method not defined");
        }


    }

    //cancel contracts for deleted user
    @PatchMapping(path = "/{id}/contracts/status")
    public void cancelContractsForDeletedUser(@PathVariable int id) {
        cusContractServ.cancelContractsForDeletedUser(id);
    }

    //Code added by Desan

    //get customer contracts by keyword
    @GetMapping(path = "/contracts", params = {"keyword"})
    public ResponseEntity<List<CustomerContract>> findByKeyword(@RequestParam String keyword){
        List<CustomerContract> contracts = cusContractServ.findByKeyword(keyword);
        return ResponseEntity.ok(contracts);
    }

    @GetMapping(path = "/contracts", params = {"status","id"})
    public ResponseEntity<List<CustomerContract>> getByStatus(@RequestParam String status, @RequestParam int id){
        List<CustomerContract> contracts = cusContractServ.getByStatus(status,id);
        return ResponseEntity.ok(contracts);
    }

    //get customer contract existence
    @RequestMapping(method = RequestMethod.HEAD, path = "/contracts/{id}")
    public ResponseEntity<Void> getCusContractExistsById(@PathVariable int id){
        if (serviceCoordinator.cusContractExistsById(id)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

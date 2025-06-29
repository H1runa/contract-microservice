package com.hiruna.contract.controller;

import com.hiruna.contract.data.WorkerContract;
import com.hiruna.contract.service.WorkerContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/worker")
public class WorkerContractController {
    private WorkerContractService workerContractService;

    public WorkerContractController(WorkerContractService workerContractService){
        this.workerContractService=workerContractService;
    }

    //create worker contract
    @PostMapping(path = "/contracts")
    public ResponseEntity<WorkerContract> createWContract(@RequestBody WorkerContract contract){
        WorkerContract c = workerContractService.createWContract(contract);
        return ResponseEntity.ok(c);
    }

    //get all worker contracts
    @GetMapping(path = "/contracts")
    public ResponseEntity<List<WorkerContract>> getAllWContracts(){
        List<WorkerContract> contracts = workerContractService.getAllWContracts();
        return ResponseEntity.ok(contracts);
    }

    //get worker contract by id
    @GetMapping(path = "/contracts/{id}")
    public ResponseEntity<WorkerContract> getWContractById(@PathVariable int id){
        WorkerContract contract = workerContractService.getWContractById(id);
        return ResponseEntity.ok(contract);
    }

    //get worker contract by customer contract id
    @GetMapping(path = "/contracts", params = {"cusContractId", "status"})
    public ResponseEntity<List<WorkerContract>> getWContractsByCusContractId(@RequestParam int cusContractId, @RequestParam String status){
        List<WorkerContract> contracts = workerContractService.getWContractsByCusContractIdAndStatus(cusContractId, status);
        return ResponseEntity.ok(contracts);
    }

    //get worker contract by worker id
    @GetMapping(path = "/contracts", params = {"workerId"})
    public ResponseEntity<List<WorkerContract>> getWContractsByWorkerId(@RequestParam int workerId){
        List<WorkerContract> contracts = workerContractService.getWContractsByWorkerId(workerId);
        return ResponseEntity.ok(contracts);
    }

    //get worker contracts by status
    @GetMapping(path = "/contracts", params = {"status"})
    public ResponseEntity<List<WorkerContract>> getWContractsByStatus(@RequestParam String status){
        List<WorkerContract> contracts = workerContractService.getWContractsByStatus(status);
        return ResponseEntity.ok(contracts);
    }

    //update worker contract
    @PutMapping(path = "/contracts")
    public ResponseEntity<WorkerContract> updateWContract(@RequestBody WorkerContract contract){
        WorkerContract c = workerContractService.updateWContract(contract);
        return ResponseEntity.ok(c);
    }

    //delete worker contract
    @DeleteMapping(path = "/contracts/{id}")
    public void deleteWContract(@PathVariable int id){
        workerContractService.deleteWContract(id);
    }

    //exists endpoint
    @GetMapping(path = "/contracts/{id}/exists")
    public ResponseEntity<Boolean> workerContractExistsById(@PathVariable int id){
        Boolean exists = workerContractService.workerContractExistsById(id);
        return ResponseEntity.ok(exists);
    }

    //cancel endpoint
    @PatchMapping(path = "/contracts/{id}/status")
    public ResponseEntity<WorkerContract> cancelWContract(@PathVariable int id, @RequestBody Map<String, String> body){
        String status = body.get("status");
        WorkerContract contract = workerContractService.cancelWContract(id, status);
        return ResponseEntity.ok(contract);
    }
}

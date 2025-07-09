package com.hiruna.contract.service.interservice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WorkerService {
    private WebClient webCLient;

    public WorkerService(WebClient.Builder builder){
        this.webCLient=builder.baseUrl("http://localhost:8087/worker-app").build();
    }

    public Boolean WokrerExists(int id){
        try{
            webCLient.head()
                    .uri("/workers/"+id)
                    .retrieve()
                    .toBodilessEntity()
                    .block();

            return true;
        } catch (WebClientResponseException.NotFound ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker was not found");
        } catch (WebClientResponseException ex){
            throw new RuntimeException("Unexpected error", ex);
        }
    }
}

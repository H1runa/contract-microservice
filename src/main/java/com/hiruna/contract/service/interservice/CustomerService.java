package com.hiruna.contract.service.interservice;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class CustomerService {
    private WebClient webClient;

    public CustomerService(WebClient.Builder builder){
        this.webClient = builder.baseUrl("http://localhost:8086/customer-ms").build();
    }

    public Boolean customerExists(int id){
        try{
            webClient.head()
                    .uri("/customers/"+id)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException.NotFound ex){
            return false;
        } catch (WebClientResponseException ex){
            throw new RuntimeException("Unexpected response error", ex);
        }
    }
}

package com.hiruna.contract.service.interservice;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class ServiceService {
    private WebClient webCLient;

    public ServiceService(WebClient.Builder builder){
        this.webCLient=builder.baseUrl("http://localhost:8088/ServiceManagement-EAD").build();
    }

    public Boolean ServiceExists(int id){
        try{
            webCLient.head()
                    .uri("/categories/"+id)
                    .retrieve()
                    .toBodilessEntity()
                    .block();

            return true;
        } catch (WebClientResponseException.NotFound ex){
            return false;
        } catch (WebClientResponseException ex){
            throw new RuntimeException("Unexpected exception", ex);
        }
    }
}

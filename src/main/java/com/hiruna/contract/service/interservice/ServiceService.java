package com.hiruna.contract.service.interservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class ServiceService {
    private WebClient webCLient;

    public ServiceService(WebClient.Builder builder, @Value("${service.service.url}") String url){
        this.webCLient=builder.baseUrl(url).build();
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

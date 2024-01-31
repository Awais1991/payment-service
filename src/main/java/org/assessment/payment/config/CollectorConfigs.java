package org.assessment.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CollectorConfigs {

    @Value("${service.student.base}")
    private String studentBaseUrl;

    @Value("${service.fee.base}")
    private String feeBaseUrl;

    @Value("${service.document.base}")
    private String documentBaseUrl;

    @Bean("studentWebClient")
    public WebClient studentWebClient(){
        return WebClient.create(studentBaseUrl);
    }

    @Bean("feeWebClient")
    public WebClient feeWebClient(){
        return WebClient.create(feeBaseUrl);
    }

    @Bean("documentWebClient")
    public WebClient documentWebClient(){
        return WebClient.create(documentBaseUrl);
    }


}

package org.assessment.payment.connector;


import org.assessment.payment.commen.ApiResponse;
import org.assessment.payment.commen.ErrorInfo;
import org.assessment.payment.dto.ReceiptDto;
import org.assessment.payment.enums.ErrorCode;
import org.assessment.payment.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assessment.payment.commen.Constants.URL_ADD_RECEIPT;

@Component
public class DocumentConnector {

    private final WebClient documentWebClient;

    public DocumentConnector(@Qualifier("documentWebClient") WebClient documentWebClient) {
        this.documentWebClient = documentWebClient;
    }

    public String generateReceipt(ReceiptDto receiptDto){

       var document =    documentWebClient.post().uri(URL_ADD_RECEIPT)
               .bodyValue(receiptDto)
                .retrieve().onStatus(
                        HttpStatusCode::is4xxClientError, response ->
                               response.bodyToMono(new ParameterizedTypeReference<ApiResponse<ErrorInfo>>(){})
                                       .flatMap(errorInfoApiResponse -> Mono.error(new ApplicationException(errorInfoApiResponse.getError().getCode(), errorInfoApiResponse.getError().getMessage())))
               ).onStatus(
                       HttpStatusCode::is5xxServerError, response -> response.bodyToMono(new ParameterizedTypeReference<ApiResponse<ErrorInfo>>(){})
                               .flatMap(errorInfoApiResponse -> Mono.error(new ApplicationException(errorInfoApiResponse.getError().getCode(), "Please try again")))
               )
               .bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>(){}).block();

         if(document!=null && document.getData()!=null) {
            return document.getData();
         }
         throw new ApplicationException(ErrorCode.UNABLE_TO_GENERATE_RECEIPT);
    }

}

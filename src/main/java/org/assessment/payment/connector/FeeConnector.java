package org.assessment.payment.connector;


import org.assessment.payment.commen.ApiResponse;
import org.assessment.payment.commen.ErrorInfo;
import org.assessment.payment.dto.FeeDto;
import org.assessment.payment.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assessment.payment.commen.Constants.URL_GET_FEES;

@Component
public class FeeConnector {

    private final WebClient feeWebClient;

    public FeeConnector(@Qualifier("feeWebClient") WebClient feeWebClient) {
        this.feeWebClient = feeWebClient;
    }

    public List<FeeDto> getFeeByFeeTypeAndGrade(String grade){

       var v =    feeWebClient.get().uri(URL_GET_FEES+grade)
                .retrieve().onStatus(
                        HttpStatusCode::is4xxClientError, response ->
                               response.bodyToMono(new ParameterizedTypeReference<ApiResponse<ErrorInfo>>(){})
                                       .flatMap(errorInfoApiResponse -> Mono.error(new ApplicationException(errorInfoApiResponse.getError().getCode(), errorInfoApiResponse.getError().getMessage())))
               ).onStatus(
                       HttpStatusCode::is5xxServerError, response -> response.bodyToMono(new ParameterizedTypeReference<ApiResponse<ErrorInfo>>(){})
                               .flatMap(errorInfoApiResponse -> Mono.error(new ApplicationException(errorInfoApiResponse.getError().getCode(), "Please try again")))
               )
               .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<FeeDto>>>(){}).block();

        return v.getData();
    }
}

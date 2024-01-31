package org.assessment.payment.connector;


import org.assessment.payment.commen.ApiResponse;
import org.assessment.payment.commen.ErrorInfo;
import org.assessment.payment.dto.StudentDto;
import org.assessment.payment.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assessment.payment.commen.Constants.URL_GET_STUDENT;

@Component
public class StudentConnector {

    private final WebClient studentWebClient;

    public StudentConnector(@Qualifier("studentWebClient") WebClient studentWebClient) {
        this.studentWebClient = studentWebClient;
    }

    public StudentDto getEnrolledStudent(String studentRollNo){
        var studentResponse =    studentWebClient.get().uri(URL_GET_STUDENT+studentRollNo)
                .retrieve().onStatus(
                        HttpStatusCode::is4xxClientError, response ->
                                response.bodyToMono(new ParameterizedTypeReference<ApiResponse<ErrorInfo>>(){})
                                        .flatMap(errorInfoApiResponse -> Mono.error(new ApplicationException(errorInfoApiResponse.getError().getCode(), errorInfoApiResponse.getError().getMessage())))
                ).onStatus(
                        HttpStatusCode::is5xxServerError, response -> response.bodyToMono(new ParameterizedTypeReference<ApiResponse<ErrorInfo>>(){})
                                .flatMap(errorInfoApiResponse -> Mono.error(new ApplicationException(errorInfoApiResponse.getError().getCode(), "Please try again")))
                )
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<StudentDto>>(){}).block();

        return studentResponse.getData();
    }

}

package com.example.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class ErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            System.out.println("Status code: " + clientHttpResponse.getStatusCode());
            System.out.println("Response" + clientHttpResponse.getStatusText());
            System.out.println(clientHttpResponse.getBody());
        }
    }
}
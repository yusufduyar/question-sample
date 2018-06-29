package com.example.client;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@RestController
@RequestMapping(value = "/callapi")
public class ClientController  {
  private RestTemplate restTemplate;

  @Autowired
  public ClientController(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
    this.restTemplate.setErrorHandler(new ErrorHandler());
  }

  @GetMapping("")
  public ResponseEntity<Object> callApi() throws Exception {
    ValidateTokenRequest request = new ValidateTokenRequest();
    request.setToken("");

    HttpEntity<ValidateTokenRequest> requestHttpEntity = new HttpEntity<>(request);

    try {
      ResponseEntity<String> responseEntity =
          restTemplate.exchange(
              "http://localhost:8080/api/auth/validatetoken",
              HttpMethod.POST,
              requestHttpEntity,
              String.class);

      String response = responseEntity.getBody();
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (HttpClientErrorException e) {
      return new ResponseEntity<>(e, e.getStatusCode());
    }
  }
}

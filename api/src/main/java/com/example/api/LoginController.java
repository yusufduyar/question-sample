package com.example.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/auth")
public class LoginController {

    @PostMapping("validatetoken")
    public ResponseEntity<ValidateTokenResponse> validateToken(
            @RequestBody ValidateTokenRequest validateTokenRequest, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(validateTokenRequest.getToken())) throw new AuthenticationFailedException("token parameter cannot be null or empty");

        Boolean isValid = true;
        ValidateTokenResponse response = new ValidateTokenResponse();
        response.setIsValid(isValid);
        response.setToken(validateTokenRequest.getToken());
        return new ResponseEntity<>(response, isValid? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }
}
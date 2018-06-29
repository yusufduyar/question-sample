package com.example.client;


import lombok.Data;

import java.util.Date;

@Data
public class ValidateTokenResponse {
    private Boolean isValid;
    private String token;
}
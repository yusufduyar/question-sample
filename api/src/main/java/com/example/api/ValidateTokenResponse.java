package com.example.api;

import lombok.Data;

@Data
public class ValidateTokenResponse {
    public Boolean isValid;
    public String token;
}


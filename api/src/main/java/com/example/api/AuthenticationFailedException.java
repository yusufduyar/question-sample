package com.example.api;

public class AuthenticationFailedException extends Exception {
  public AuthenticationFailedException(String message){
    super(message);
  }
}

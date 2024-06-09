package com.lvh.service;

import com.lvh.dto.AuthenticationRequest;
import com.lvh.dto.AuthenticationResponse;
import com.lvh.dto.RegistrationRequest;
import jakarta.mail.MessagingException;

public interface AuthenticationService {
    void register(RegistrationRequest request) throws MessagingException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void activateAccount(String token) throws MessagingException;
}

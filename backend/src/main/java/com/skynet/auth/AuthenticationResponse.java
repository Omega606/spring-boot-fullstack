package com.skynet.auth;

import com.skynet.customer.CustomerDTO;

public record AuthenticationResponse(
        String token,
        CustomerDTO customerDTO) {
}

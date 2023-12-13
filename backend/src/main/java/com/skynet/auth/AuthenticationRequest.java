package com.skynet.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}

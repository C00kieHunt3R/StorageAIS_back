package org.ssau.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class JwtResponse {

    private final String type = "Bearer";
    private String token;
    private String username;
    private String employeeType;

    public JwtResponse(String username, String employeeType, String token) {
        this.username = username;
        this.employeeType = employeeType;
        this.token = String.format("%s %s", type, token);
    }
}

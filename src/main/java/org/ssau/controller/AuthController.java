package org.ssau.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssau.model.Employee;
import org.ssau.model.EmployeeType;
import org.ssau.repository.EmployeeRepository;
import org.ssau.security.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public JwtResponse authenticate(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        EmployeeDetailsImpl employeeDetails = (EmployeeDetailsImpl) authentication.getPrincipal();
        String employeeType = employeeDetails.getAuthorities().toString();

        return new JwtResponse(
                employeeDetails.getUsername(),
                employeeType,
                jwt);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegistrationRequest registrationRequest) {
        Employee employee = new Employee();
        employee.setUsername(registrationRequest.getUsername());
        employee.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        String reqRoles = registrationRequest.getRole();
        EmployeeType role;
        if (reqRoles != null)
            role = reqRoles.toUpperCase() == "ADMIN"? EmployeeType.ROLE_ADMIN : EmployeeType.ROLE_MERCHANDISER;
        else role = EmployeeType.ROLE_MERCHANDISER;
        employee.setRole(role);
        employeeRepository.save(employee);
    }
}

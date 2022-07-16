package org.ssau.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssau.model.Employee;
import org.ssau.repository.EmployeeRepository;
import org.ssau.security.EmployeeDetailsImpl;


@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = (Employee) employeeRepository.findEmployeeByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с таким ником не существует:" + username));
        return EmployeeDetailsImpl.build(employee);
    }
}

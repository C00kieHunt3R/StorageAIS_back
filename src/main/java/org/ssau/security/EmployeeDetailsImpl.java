package org.ssau.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.ssau.model.Employee;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static EmployeeDetailsImpl build(Employee employee) {
        List<GrantedAuthority> authorities = List.of(employee.getRole()).stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());

        return new EmployeeDetailsImpl(
                employee.getUsername(),
                employee.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

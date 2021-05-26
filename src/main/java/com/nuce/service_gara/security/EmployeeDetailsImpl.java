package com.nuce.service_gara.security;

import com.nuce.service_gara.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsImpl implements UserDetails {

    private int id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static EmployeeDetailsImpl build(Employee employee, Set<GrantedAuthority> authorities) {
        return new EmployeeDetailsImpl(
                employee.getEmployeeId(),
                employee.getUsername(),
                employee.getPassword(),
                authorities);
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.emptyList();
//    }

    public int getId() {
        return id;
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

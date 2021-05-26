package com.nuce.service_gara.service.serviceImpl;

import com.nuce.service_gara.model.Employee;
import com.nuce.service_gara.model.Role;
import com.nuce.service_gara.model.request.CreateEmployeeRequestDTO;
import com.nuce.service_gara.model.request.LoginRequestDTO;
import com.nuce.service_gara.model.response.JwtResponse;
import com.nuce.service_gara.repository.EmployeeRepo;
import com.nuce.service_gara.security.EmployeeDetailsImpl;
import com.nuce.service_gara.security.jwt.JwtUtils;
import com.nuce.service_gara.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> login(LoginRequestDTO loginRequestDTO) {
        try {
            System.out.println("in " + loginRequestDTO.getUsername());
            log.info("User Login : " + loginRequestDTO.getUsername());
            Optional<Employee> employee = employeeRepo.findByUsername(loginRequestDTO.getUsername());
            if (!employee.isPresent()) {
                log.info("USER_NOT_FOUND");
                return new ResponseEntity<>("employee don't exist", HttpStatus.BAD_REQUEST);
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Trả về jwt cho người dùng.
            String jwt = jwtUtils.generateToken((EmployeeDetailsImpl) authentication.getPrincipal());
            JwtResponse jwtResponse = new JwtResponse(jwt, "Bearer", employee.get().getUsername(), employee.get().getRole());
            return ResponseEntity.ok(jwtResponse);
        } catch (AuthenticationException e) {
            log.error("USERNAME_OR_PASSWORD_INCORRECT", e);
            return new ResponseEntity<>("USERNAME_OR_PASSWORD_INCORRECT", HttpStatus.UNAUTHORIZED);
        } catch (IllegalArgumentException e) {
            log.error("INTERNAL_SERVER_ERROR", e);
            return new ResponseEntity<>("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> createOrUpdate(CreateEmployeeRequestDTO employeeRequest) {
        Employee employee = null;
        try {
            if(0==employeeRequest.getEmployeeId()){
                employee = new Employee();
            }else{
                employee = em.getReference(Employee.class,employeeRequest.getEmployeeId());
            }

            employee.setName(employeeRequest.getName());
            employee.setUsername(employeeRequest.getUsername());

            if(!"".equals(employeeRequest.getPassword())){
                employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
            }

            employee.setGender(employeeRequest.getGender());
            employee.setPosition(employeeRequest.getPosition());
            employee.setRole((Set<Role>) employeeRequest.getRole());

            em.merge(employee);

            return new ResponseEntity<>("SUCCESSFUL", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
        }
    }

//    public ResponseEntity<?> create(CreateEmployeeRequestDTO employeeRequest) {
//        Optional<Employee> employee = employeeRepo.findByUsername(employeeRequest.getUsername());
//        if (!employee.isPresent()) {
//            Set<Role> roles = employeeRequest.getRole();
//            Employee newEmployee = new Employee();
//            newEmployee.setName(employeeRequest.getName());
//            newEmployee.setUsername(employeeRequest.getUsername());
//            newEmployee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
//            newEmployee.setGender(employeeRequest.getGender());
//            newEmployee.setPosition(employeeRequest.getPosition());
//            newEmployee.setRole(roles);
//            employeeRepo.save(newEmployee);
//
//            return new ResponseEntity<>("SUCCESSFUL", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
//        }
//    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Employee> employee = employeeRepo.findAll();

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(CreateEmployeeRequestDTO employeeRequest) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getByUsername(String username){
        Optional<Employee> employee = employeeRepo.findByUsername(username);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("FAIL", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> search(String query){
        String sql = "From Employee e where e.name like '%"+query+"%' or e.username like '%"+query+"%'";

        List<Employee> employee = em.createQuery(sql).getResultList();

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

//    public ResponseEntity<?> delete(String username){
//        Optional<Employee> employee = employeeRepo.findByUsername(username);
//        if (employee.isPresent()) {
//            employeeRepo.delete(employee);
//            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("FAIL", HttpStatus.NOT_FOUND);
//        }
//    }
}

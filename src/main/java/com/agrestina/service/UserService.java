package com.agrestina.service;

import com.agrestina.domain.user.User;
import com.agrestina.dto.user.GetUserDTO;
import com.agrestina.dto.user.LoginRequestDTO;
import com.agrestina.dto.user.RegisterRequestDTO;
import com.agrestina.dto.user.ResponseDTO;
import com.agrestina.infra.security.TokenService;
import com.agrestina.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    public ResponseDTO login(@RequestBody LoginRequestDTO dto) {
        User user = this.repository.findByLogin(dto.login()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(dto.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token, user.getUserRole().toString())).getBody();
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("Tentativa de login com falha", "", "")).getBody();
        }
    }

    public ResponseDTO register(RegisterRequestDTO dto) {
        Optional<User> user = this.repository.findByLogin(dto.login());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setName(dto.name());
            newUser.setLogin(dto.login());
            newUser.setPassword(passwordEncoder.encode(dto.password()));
            newUser.setUserRole(dto.userRole());
            newUser.setActive(true);
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token, newUser.getUserRole().toString())).getBody();

        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("Usuário já cadastrado", "", "")).getBody();
        }
    }

    public Page<GetUserDTO> listActive(Pageable pagination) {
        return repository.findActive(pagination).map(GetUserDTO::new);
    }

    public Page<GetUserDTO> listAll(Pageable pagination) {
        return repository.findAll(pagination).map(GetUserDTO::new);
    }

    public void delete(String login){
        var user = repository.findByLogin(login).get();
        user.disabled();
    }

    public void activate(String login){
        var user = repository.findUser(login).get();
        user.activate();
    }

}



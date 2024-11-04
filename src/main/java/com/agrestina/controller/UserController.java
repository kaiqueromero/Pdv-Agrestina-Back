package com.agrestina.controller;

import com.agrestina.domain.user.User;
import com.agrestina.dto.user.GetUserDTO;
import com.agrestina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<GetUserDTO>> ListActiveUsers(Pageable pagination){
        var user = this.userService.listActive(pagination);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<GetUserDTO>> ListAllUsers(Pageable pagination){
        var user = this.userService.listAll(pagination);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{login}")
    @Transactional
    public ResponseEntity<GetUserDTO> delete(@PathVariable String login) {
        this.userService.delete(login);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{login}")
    @Transactional
    public ResponseEntity<GetUserDTO> activate(@PathVariable String login) {
        this.userService.activate(login);
        return ResponseEntity.noContent().build();
    }
}
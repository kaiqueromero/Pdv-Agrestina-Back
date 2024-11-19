package com.agrestina.controller;

import com.agrestina.domain.client.Client;
import com.agrestina.dto.client.ClientRequestDTO;
import com.agrestina.dto.client.ClientResponseDTO;
import com.agrestina.dto.user.GetUserDTO;
import com.agrestina.dto.user.RegisterRequestDTO;
import com.agrestina.dto.user.ResponseDTO;
import com.agrestina.repository.ClientRepository;
import com.agrestina.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<ClientResponseDTO> register (@RequestBody ClientRequestDTO body){
        var newClient = this.clientService.register(body);
        log.info("Cliente cadastrado com sucesso.");
        return ResponseEntity.ok(newClient);
    }

    @GetMapping("/active")
    public ResponseEntity<Page<ClientResponseDTO>> ListActiveClients(Pageable pagination){
        var client = this.clientService.listActive(pagination);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/inactive")
    public ResponseEntity<Page<ClientResponseDTO>> ListInactiveUsers(Pageable pagination){
        var client = this.clientService.listInactive(pagination);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ClientResponseDTO>> ListAllUsers(Pageable pagination){
        var client = this.clientService.listAll(pagination);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("delete/{name}")
    @Transactional
    public ResponseEntity<ClientResponseDTO> delete(@PathVariable String name) {
        var client = this.clientService.delete(name);
        return ResponseEntity.ok(new ClientResponseDTO(client));
    }

    @PatchMapping("activate/{name}")
    @Transactional
    public ResponseEntity<ClientResponseDTO> activate(@PathVariable String name) {
        var client = this.clientService.activate(name);
        return ResponseEntity.ok(new ClientResponseDTO(client));
    }
}

package com.agrestina.controller;

import com.agrestina.domain.client.Client;
import com.agrestina.domain.user.User;
import com.agrestina.dto.order.OrderDTO;
import com.agrestina.dto.order.RegisterOrderDTO;
import com.agrestina.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService service;

    //@Autowired
    //private EmailPedidoRealizado email;

    @PostMapping
    public ResponseEntity<OrderDTO> register(@RequestBody RegisterOrderDTO dto, User user, Client client) {
        var order = this.service.register(dto, user, client);
        //System.out.println("Thread do controller: " + Thread.currentThread().getName());
        //email.enviar(order, user);
        return ResponseEntity.ok(order);
    }
}

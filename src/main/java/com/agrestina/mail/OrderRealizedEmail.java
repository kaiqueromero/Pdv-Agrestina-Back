package com.agrestina.mail;//package com.agrestina.mail;
//
//import com.agrestina.domain.client.Client;
//import com.agrestina.domain.user.User;
//import com.agrestina.dto.order.OrderDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderRealizedEmail {
//
//    @Autowired
//    private EmailSender sender;
//
//    public void send(OrderDTO dto, User user, Client client) {
//        sender.sendEmail(
//                "Pedido efetuado com sucesso na Adopet Store",
//                user.getLogin(),
//                "Olá! " + "!\n\nSeu pedido foi registrado. Itens: \n" + dto.items());
//        System.out.println("Thread do email: " + Thread.currentThread().getName());
//    }
//}

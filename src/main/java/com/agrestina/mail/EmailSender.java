package com.agrestina.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

        private JavaMailSender emailSender;

        public void sendEmail(String subject, String to, String composeEmail) {
            try {
            var email = new SimpleMailMessage();
            email.setFrom("kaiqueebr729@gmail.com");
            email.setSubject(subject);
            email.setTo(to);
            email.setText(composeEmail);
            emailSender.send(email);
                System.out.println("Enviando email!");
                System.out.println(composeEmail);

            } catch (Exception e) {

                throw new RuntimeException("Erro ao enviar email!", e);
            }
        }

}


package com.Gestor.Service.Impl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatario);
        email.setSubject(asunto);
        email.setText(mensaje);

        mailSender.send(email);
    }
}


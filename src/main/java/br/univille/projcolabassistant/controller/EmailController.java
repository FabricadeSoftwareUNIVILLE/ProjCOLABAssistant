package br.univille.projcolabassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmailController {
    @Autowired private JavaMailSender mailSender;


    @RequestMapping(path = "/enviar_email", method = RequestMethod.POST)
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setText("Test Spring Boot Application");
        message.setTo("luan.fernando@univille.br");
        message.setFrom("teste@gmail.com");
        message.setSubject("Teste spring boot");


        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

}


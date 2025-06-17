package com.seuprojeto.contato.service;

import com.seuprojeto.contato.dto.ContatoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarMensagem(ContatoDTO contato) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo("fabio.prates.moreira@gmail.com");
            helper.setSubject("Novo Contato: " + contato.getTitulo());
            helper.setText(
                    "Empresa: " + contato.getEmpresa() + "<br>" +
                            "Email: " + contato.getEmail() + "<br><br>" +
                            "<b>Mensagem:</b><br>" + contato.getMensagem(),
                    true // HTML ativado
            );
            helper.setFrom("fabio.prates.moreira@gmail.com");

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }
}


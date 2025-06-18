package com.seuprojeto.contato.controller;

import com.seuprojeto.contato.dto.ContatoDTO;
import com.seuprojeto.contato.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contato")
@CrossOrigin(origins = "https://fabiopratesmoreira.com.br")
public class ContatoController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> receberMensagem(@RequestBody ContatoDTO contato) {
        System.out.println("Recebido contato de: " + contato.getEmpresa());
        emailService.enviarMensagem(contato);
        return ResponseEntity.ok("Mensagem enviada com sucesso!");
    }
}

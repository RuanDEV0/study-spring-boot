package io.github.ruan.arquiteturaspring.todo.components;

import org.springframework.stereotype.Component;

@Component
public class MailSender {
    public void sender(String message) {
        System.out.println("Mensagem " + message + "enviada com sucesso!");
    }
}

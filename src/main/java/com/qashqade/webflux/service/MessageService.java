package com.qashqade.webflux.service;

import com.qashqade.webflux.domain.Message;
import com.qashqade.webflux.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Level;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Flux<Message> allMessages(int limit) {
        long currentTime = System.currentTimeMillis();
        return messageRepository.findAllLimitBy(PageRequest.of(0,limit))
            .log("MessageService.allMessages", Level.INFO)
            .doOnComplete(() -> System.out.println("webflux " + limit + " Yes! " + (System.currentTimeMillis() - currentTime)/1000.0))
            .doOnError((e) -> System.out.println("webflux " + limit + " No! " + e.getMessage()));


    }

    public Mono<Message> getMessageById(Long id) {
        long currentTime = System.currentTimeMillis();
        return messageRepository.findById(id)
            .log("MessageService.getMessageById", Level.INFO)
            .doOnSuccess(m -> System.out.println("webflux id " + id + " Yes! " + (System.currentTimeMillis() - currentTime)/1000.0))
            .doOnError((e) -> System.out.println("webflux id " + id + " No! " + e.getMessage()));
    }

    public Mono<Message> saveMessage(Message message) {
        return messageRepository.save(message);
    }
}

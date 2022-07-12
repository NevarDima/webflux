package com.qashqade.webflux.service;

import com.qashqade.webflux.domain.Message;
import com.qashqade.webflux.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Flux<Message> allMessages(){
        return messageRepository.findAll();
    }

    public Mono<Message> getMessageById(Long id){
        return messageRepository.findById(id);
    }

    public Mono<Message> saveMessage(Message message){
        return messageRepository.save(message);
    }
}

package com.qashqade.webflux.service;

import com.qashqade.webflux.domain.Message;
import com.qashqade.webflux.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

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
            .log("MessageService.allMessages", Level.INFO, SignalType.ON_ERROR)
            .doOnComplete(() -> System.out.printf("%s messages have been got from postgres db in %s seconds\n", limit,
                (System.currentTimeMillis() - currentTime)/1000.0))
            .doOnError((e) -> System.out.printf("%s can't be obtained from postgres db!\nBecause:\n%s",limit,e.getMessage()));


    }

    public Mono<Message> getMessageById(Long id) {
        long currentTime = System.currentTimeMillis();
        return messageRepository.findById(id)
            .log("MessageService.getMessageById", Level.INFO, SignalType.ON_ERROR)
            .doOnSuccess(m -> System.out.printf("Message id %s has been got from postgres db in %s seconds!\n", id,
                (System.currentTimeMillis() - currentTime)/1000.0))
            .doOnError((e) -> System.out.printf("Message id %s can't be obtained from postgres db!\nBecause:\n%s", id, e.getMessage()));
    }

    public Mono<Message> saveMessage(Message message) {
        return messageRepository.save(message);
    }
}

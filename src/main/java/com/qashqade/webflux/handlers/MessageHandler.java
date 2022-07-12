package com.qashqade.webflux.handlers;

import com.qashqade.webflux.domain.Message;
import com.qashqade.webflux.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageHandler {

    private final MessageService messageService;

    public MessageHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    public Mono<ServerResponse> list(ServerRequest request) {

//        Flux<Message> body = Flux.just("1", "2", "3").map(Message::new);

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(/*body*/ messageService.allMessages(), Message.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(messageService.getMessageById(Long.valueOf(request.pathVariable("id"))), Message.class);
    }

    public Mono<ServerResponse> saveMessage (ServerRequest request){

        Mono<Message> publisher = messageService.saveMessage(
            new Message(
                "trx",
                "subTransaction",
                "appliedDateChoice",
                true,
                "fxRateSource",
                "targetCcy",
                "fxTable",
                "fx",
                "deals",
                "dealGroups",
                "vintageYearNames",
                "lps",
                "lpgroups",
                "splitTypes",
                "allDeals",
                "amount",
                "originalAmount",
                "appliedDate",
                "allLps",
                "impactName",
                "uid",
                "ccy",
                "gpid",
                "gahid",
                "dealAllocationRule",
                "lpAllocationRule",
                "customfields",
                false,
                false
            )
        );
        return ServerResponse
            .ok()
            .body(publisher, Message.class);
    }
}

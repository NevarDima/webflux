package com.qashqade.webflux.handlers;

import com.qashqade.webflux.domain.Message;
import com.qashqade.webflux.service.MessageService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageHandler {
    private static final String DEFAULT_LIMIT = "100";

    private final MessageService messageService;

    public MessageHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        var limit = Integer.parseInt(request.queryParam("limit").orElse(DEFAULT_LIMIT));
//        messageService.allMessages(limit);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_NDJSON)
//                .body(BodyInserters.fromValue("done"));
            .body( messageService.allMessages(limit), Message.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_NDJSON)
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

package com.qashqade.webflux.config;

import com.qashqade.webflux.handlers.MessageHandler;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class MessageRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllMessagesRoute(MessageHandler messageHandler) {
        return RouterFunctions
            .route(GET("/messages"), messageHandler::list);
    }

    @Bean
    public RouterFunction<ServerResponse> getMessageByIdRoute(MessageHandler messageHandler) {
        return RouterFunctions
            .route(GET("/messages/{id}"), messageHandler::getById);
    }

    @Bean
    public RouterFunction<ServerResponse> saveMessageRoute(MessageHandler messageHandler) {
        return RouterFunctions
            .route(POST("/message"), messageHandler::saveMessage);
    }
}

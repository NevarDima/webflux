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

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration(proxyBeanMethods = false)
public class MessageRouter {

    @Autowired
    MeterRegistry meterRegistry;

    @Bean
    public RouterFunction<ServerResponse> route(MessageHandler messageHandler) {
//        Timer timer = meterRegistry.timer("timeToGetId", "routeGET");
//        TODO add timer for getById
        return RouterFunctions
            .route(GET("/messages"), messageHandler::list)
            .andRoute(GET("/messages/{id}"), messageHandler::getById)
            .andRoute(POST("/message"), messageHandler::saveMessage);
    }
}

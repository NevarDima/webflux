package com.qashqade.webflux.config;

import com.qashqade.webflux.handlers.TransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration(proxyBeanMethods = false)
public class TransactionRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllTransactionRoute(TransactionHandler transactionHandler) {
        return RouterFunctions
            .route(GET("/transactions"), transactionHandler::list);
    }

    @Bean
    public RouterFunction<ServerResponse> getTransactionByIdRoute(TransactionHandler transactionHandler) {
        return RouterFunctions
            .route(GET("/transactions/{id}"), transactionHandler::getById);
    }

    @Bean
    public RouterFunction<ServerResponse> saveTransactionRoute(TransactionHandler transactionHandler) {
        return RouterFunctions
            .route(POST("/transaction"), transactionHandler::saveTransaction);
    }
}

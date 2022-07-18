package com.qashqade.webflux.handlers;

import com.qashqade.webflux.domain.Transaction;
import com.qashqade.webflux.service.TransactionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TransactionHandler {

    private static final String DEFAULT_LIMIT = "100";

    private final TransactionService transactionService;

    public TransactionHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        var limit = Integer.parseInt(request.queryParam("limit").orElse(DEFAULT_LIMIT));
//        messageService.allMessages(limit);
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_NDJSON)
//                .body(BodyInserters.fromValue("done"));
            .body(transactionService.allTransactions(limit), Transaction.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_NDJSON)
            .body(transactionService.getTransactionById(Long.valueOf(request.pathVariable("id"))), Transaction.class);
    }

    public Mono<ServerResponse> saveTransaction(ServerRequest request) {

        Mono<Transaction> publisher = transactionService.saveTransaction(
            new Transaction(
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
            .body(publisher, Transaction.class);
    }
}

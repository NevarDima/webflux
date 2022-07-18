package com.qashqade.webflux.service;

import com.qashqade.webflux.domain.Transaction;
import com.qashqade.webflux.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.logging.Level;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Flux<Transaction> allTransactions(int limit) {
        long currentTime = System.currentTimeMillis();
        return transactionRepository.findAllLimitBy(PageRequest.of(0, limit))
            .log("TransactionService.allTransactions", Level.INFO, SignalType.ON_ERROR)
            .doOnComplete(() -> log.debug("{} transactions have been got from postgres db in {} seconds!", limit,
                (System.currentTimeMillis() - currentTime) / 1000.0))
            .doOnError((e) -> log.error("{} can't be obtained from postgres db!", limit, e));


    }

    public Mono<Transaction> getTransactionById(Long id) {
        long currentTime = System.currentTimeMillis();
        return transactionRepository.findById(id)
            .log("TransactionService.getTransactionById", Level.INFO, SignalType.ON_ERROR)
            .doOnSuccess(m -> log.debug("Transaction id {} has been got from postgres db in {} seconds!", id,
                (System.currentTimeMillis() - currentTime) / 1000.0))
            .doOnError((e) -> log.error("Transaction id {} can't be obtained from postgres db!", id, e));
    }

    public Mono<Transaction> saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}

package com.qashqade.webflux.repository;

import com.qashqade.webflux.domain.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Long> {

    Flux<Transaction> findAllLimitBy(Pageable pageable);

}

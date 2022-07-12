package com.qashqade.webflux.repository;

import com.qashqade.webflux.domain.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {

    Flux<Message> findAllLimitBy(Pageable pageable);

}

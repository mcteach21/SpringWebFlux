package mc.apps.spring.webflux.service;

import mc.apps.spring.webflux.model.Todo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITodoService {

    Flux<Todo> findAll();
    Mono<Todo> findById(String id);
    Flux<Todo> findByTitle(String title);

    Mono<Todo> create(Todo todo);
    Mono<Todo> create(Mono<Todo> todoStream);

    Mono<Todo> update(Todo todo);
    Mono<Void> delete(String id);
}

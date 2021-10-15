package mc.apps.spring.webflux.model;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITodoService {

    Flux<Todo> findAll();
    Mono<Todo> findById(Integer id);
    Flux<Todo> findByTitle(String title);

    Mono<Todo> create(Mono<Todo> e);
    Mono<Todo> create(Todo todo);

//    <S extends Todo> S save(S item);
//    Mono<Todo> update(Flux<Todo> e);

    Mono<Todo> update(Todo todo);
    Mono<Void> delete(Integer id);
}

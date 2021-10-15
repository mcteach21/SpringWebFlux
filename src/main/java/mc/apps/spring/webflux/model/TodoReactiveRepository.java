package mc.apps.spring.webflux.model;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TodoReactiveRepository extends ReactiveMongoRepository<Todo, Integer> {
    Flux<Todo> findByTitle(String title);

//    findAll().filter(t->t.getTitle().contains(title));
}

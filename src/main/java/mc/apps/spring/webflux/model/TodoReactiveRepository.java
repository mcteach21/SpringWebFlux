package mc.apps.spring.webflux.model;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TodoReactiveRepository extends ReactiveMongoRepository<Todo, String> {

    @Query("{ 'title' : { '$regex' : ?0 , $options: 'i'}}")
    Flux<Todo> findByTitle(String title);

}

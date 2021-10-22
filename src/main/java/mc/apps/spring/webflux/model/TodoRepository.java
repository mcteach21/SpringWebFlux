package mc.apps.spring.webflux.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TodoRepository {
    private static final Logger logger = LogManager.getLogger(TodoRepository.class);

    /*
    for tests
     */
    static Flux<Todo> toDoList = Flux.just(
            new Todo( "Learn Spring Boot",true),
            new Todo("Practice Spring Boot",false)
    );


//  template.insertAll(flux).subscribe();
    public Flux<Todo> findAll() {
        logger.info("findall..");
        toDoList.subscribe(
                System.out::println
        );
        return toDoList;
    }
//    public Mono<Todo> findById(int id) {
//        return toDoList.filter(t->t.getId()==id).next();
//    }

}

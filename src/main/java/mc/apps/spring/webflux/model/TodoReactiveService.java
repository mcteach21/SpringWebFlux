package mc.apps.spring.webflux.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoReactiveService implements ITodoService{
    private static final Logger logger = LogManager.getLogger(TodoReactiveService.class);

    final
    TodoReactiveRepository todoReactiveRepository;
    public TodoReactiveService(TodoReactiveRepository todoReactiveRepository) {
        this.todoReactiveRepository = todoReactiveRepository;
    }

    @Override
    public Flux<Todo> findAll() {
        Flux<Todo> todos = todoReactiveRepository.findAll();

        logger.info("findAll..");
        todos.subscribe(logger::info);

        return todos;
    }

    @Override
    public Mono<Todo> findById(Integer id) {
        return todoReactiveRepository.findById(id);
    }

    @Override
    public Flux<Todo> findByTitle(String title) {
        return todoReactiveRepository.findByTitle(title);
    }

    @Override
    public Mono<Todo> create(Mono<Todo> e) {
        logger.info("create 0.."+e);
        return e.doOnNext(todoReactiveRepository::insert);
    }

    @Override
    public Mono<Todo> create(Todo todo) {
        Mono<Todo> _todo = todoReactiveRepository.insert(todo);
        logger.info("create 1.."+_todo);
        return _todo;
    }

    public Todo create2(Todo todo) {
        logger.info("create 2.."+todo);
        todoReactiveRepository.insert(todo)
                            .subscribe(result -> logger.info("Entity has been saved: {}", result));
        return todo;
    }

//    @Override
//    public <S extends Todo> S save(S item) {
//        logger.info("insert.."+item);
//        todoReactiveRepository
//                .insert(item)
//                .subscribe(result -> logger.info("Entity has been saved: {}", result));
//
//        return item;
//    }

    @Override
    public Mono<Todo> update(Todo todo) {
        return todoReactiveRepository.save(todo);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return todoReactiveRepository.deleteById(id);
    }
}

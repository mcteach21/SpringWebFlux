package mc.apps.spring.webflux.service;

import mc.apps.spring.webflux.model.Todo;
import mc.apps.spring.webflux.model.TodoReactiveRepository;
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
    public Flux<Todo> findAll() { return todoReactiveRepository.findAll(); }
    public Object findAllDelayed() {
        return findAll().map(t->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
            return t;
        });
    }
    @Override
    public Mono<Todo> findById(String id) {
        return todoReactiveRepository.findById(id);
    }

    @Override
    public Flux<Todo> findByTitle(String title) {
        return todoReactiveRepository.findByTitle(title);
    }

    @Override
    public Mono<Todo> create(Todo todo) {
        logger.info("create todo.."+ todo);
        return todoReactiveRepository.insert(todo);
    }
    @Override
    public Mono<Todo> create(Mono<Todo> todoStream) {
        logger.info("create todo from Mono.."+todoStream);
        return todoReactiveRepository.insert(todoStream).next();

//        return todoStream.doOnNext(todo-> {
//            todoReactiveRepository.insert(todo);
//            logger.info("Todo has been saved: {}!", todo);
//        }).thenEmpty(Mono.empty());
    }

    @Override
    public Mono<Todo> update(Todo todo) {
        return todoReactiveRepository.save(todo);
    }
    @Override
    public Mono<Void> delete(String id) {
        return todoReactiveRepository.deleteById(id).then();
    }
}

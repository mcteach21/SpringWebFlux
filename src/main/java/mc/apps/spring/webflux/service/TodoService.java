package mc.apps.spring.webflux.service;

import mc.apps.spring.webflux.model.Todo;
import mc.apps.spring.webflux.model.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public Flux<Todo> findAll(){
        return todoRepository.findAll();
    }
    public Flux<String> findAllNames() {
        Flux<Todo> result = findAll();
        return result.map(it -> it.getTitle());
    }


//    public Mono<Todo> findById(int id){
//        return todoRepository.findById(id);
//    }

//    public Object save(Flux<Todo> todo) {
//        return todoRepository.save(todo);
//    }
//    public Flux<Todo> saveAll(Flux<Todo> todos){
//        return todoRepository.saveAll(todos);
//    }
//    public Mono<Void> deleteById(int id){
//        return todoRepository.deleteById(id);
//    }


}

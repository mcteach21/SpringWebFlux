package mc.apps.spring.webflux.controllers;

import mc.apps.spring.webflux.model.Todo;
import mc.apps.spring.webflux.service.TodoReactiveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TodoRestController {
    private static final Logger logger = LogManager.getLogger(TodoRestController.class);

    final
    TodoReactiveService todoService;
    public TodoRestController(TodoReactiveService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/api/todos")
    public Flux<Todo> getAllTodos(){
        logger.info("RestController..getAllTodos");
        return todoService.findAll();
    }
    @GetMapping("/api/todos/{id}")
    private Mono<Todo> getTodoById(@PathVariable String id) {
        return todoService.findById(id);
    }

    @GetMapping("/api/todos/title/{title}")
    private Flux<Todo> getTodoByTitle(@PathVariable String title) {
        return todoService.findByTitle(title);
    }

//    @PostMapping(value = "/api/todos/save", consumes = { MediaType.APPLICATION_JSON_VALUE })
//    @ResponseStatus(code = HttpStatus.CREATED)
//    Mono<Todo> saveTodo(@RequestBody Mono<Todo> todoStream) {
//        logger.info("RestController..save todo (from Mono)");
//        return todoService.create(todoStream);
//    }

    @PostMapping(value = "/api/todos/create", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Todo> saveTodo(@RequestBody Todo todo) {
        logger.info("RestController..save todo");
        return todoService.create(todo);
    }



//    @PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE })
//    public Mono<Todo> addTodo(@RequestBody Todo todo, UriComponentsBuilder builder) {
//        return todoService.create(todo);
////                .map(newTodo -> {
////                    HttpHeaders headers = new HttpHeaders();
////                    headers.setLocation(builder.path("/Todos/{id}").buildAndExpand(newTodo.getId()).toUri());
////                    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
////                });
//    }
}

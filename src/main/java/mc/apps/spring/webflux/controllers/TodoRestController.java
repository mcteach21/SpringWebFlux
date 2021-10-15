package mc.apps.spring.webflux.controllers;

import mc.apps.spring.webflux.model.Todo;
import mc.apps.spring.webflux.model.TodoReactiveService;
import mc.apps.spring.webflux.router.WebFluxRouter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TodoRestController {
    private static final Logger logger = LogManager.getLogger(WebFluxRouter.class);

    final
    TodoReactiveService todoService;
    public TodoRestController(TodoReactiveService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/list")
    public Flux<Todo> getAllTodos(){
        return todoService.findAll();
    }
    @GetMapping("/{id}")
    private Mono<Todo> getTodoById(@PathVariable int id) {
        return todoService.findById(id);
    }

    @PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Todo> saveTodo(@RequestBody Todo todo) { //@Valid
        logger.info("todo : "+todo);
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

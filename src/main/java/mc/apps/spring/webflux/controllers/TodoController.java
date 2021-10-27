package mc.apps.spring.webflux.controllers;

import mc.apps.spring.webflux.model.Todo;
import mc.apps.spring.webflux.service.TodoReactiveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.result.view.RedirectView;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

@Controller
public class TodoController {
    private static final Logger logger = LogManager.getLogger(TodoController.class);

    final
    TodoReactiveService todoService;
    public TodoController(TodoReactiveService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String index(final Model model){

        logger.info("Controller..index (findAllDelayed()=>model)");

        /**
         * ReactiveDataDriverContextVariable :  classe Thymeleaf en mode Data-Driven, enveloppant une instance Flux.
         * les données émises de manière asynchrone côté serveur sont transmises séquentiellement côté affichage (vue) par SSE (Server Sent Event)
         */
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(todoService.findAllDelayed(), 1);


        model.addAttribute("todos", reactiveDataDrivenMode);
//        model.addAttribute("found", null);

        return "index";
    }

    @GetMapping("/todos/delete/{id}")
    public RedirectView delete(@PathVariable String id, final Model model){
        logger.info("Controller..delete :"+id);
        todoService.delete(id).subscribe();
        return new RedirectView("/");
    }
    @GetMapping("/todos/done/{id}")
    public RedirectView done(@PathVariable String id, final Model model){
        logger.info("Controller..done :"+id);

//        Mono<Todo> found = todoService.findById(id);
//        found.subscribe(logger::info);
//
//        found.map(todo->{
//            todo.setDone(true);
//            todoService.update(todo);
//            return todo;
//        }).subscribe();

        Mono<Todo> todoMono = todoService.findById(id)
                .map(t -> {
                    t.setDone(true);
                    logger.info("found : "+t);
                    return t;
                }).flatMap(todoService::update);

        todoMono.subscribe();

        return new RedirectView("/");
    }

    @GetMapping("/test")
    public String test(){
//        throw new RuntimeException("test exception");
        return "test";
    }

    @GetMapping("/websocket")
    public String websocket(){
        return "websocket";
    }
}

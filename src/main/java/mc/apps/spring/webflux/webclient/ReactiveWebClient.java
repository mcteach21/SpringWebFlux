package mc.apps.spring.webflux.webclient;

import mc.apps.spring.webflux.model.Todo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ReactiveWebClient {
    private static final Logger logger = LogManager.getLogger(ReactiveWebClient.class);

    WebClient client = WebClient.create("http://localhost:8080");

    public void getAll(){
        logger.info("ReactiveWebClient getAll()..");

        Flux<Todo> todoFlux = client.get()
                .uri("/api/todos/")
                .retrieve()
                .bodyToFlux(Todo.class);

        todoFlux.subscribe(logger::info);
    }
//    public void create(){
//        logger.info("ReactiveWebClient create()..");
//
//        Todo todo = new Todo(0,"WebClient practice 3!!",false);
//
//        Mono<Todo> todoMono = client.post()
//                .uri("/api/todos/save")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
////                .body(Mono.just(todo), Todo.class)
//                .bodyValue(todo)
//                .retrieve()
//                .bodyToMono(Todo.class);
//
//        todoMono.subscribe(logger::info);
//    }
    public void create(){
        logger.info("ReactiveWebClient createWithMono()..");

        String tm = DateTimeFormatter.ofPattern("HHmmss").format(LocalDateTime.now());
        Todo todo = new Todo("WebClient demo_"+tm,false);

        Mono<Todo> todoMono = client.post()
                .uri("/todos/create") //Webflux Router
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .body(Mono.just(todo), Todo.class)
                .bodyValue(todo)
                .retrieve()
                .bodyToMono(Todo.class);

        todoMono.subscribe(logger::info);
    }

    public void findByTitle(String title) {
        logger.info("ReactiveWebClient find()..");

        Flux<Todo> todoFlux = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/todos/title/{title}")
                        .build(title))
                .retrieve()
                .bodyToFlux(Todo.class);

        todoFlux.subscribe(logger::info);
    }
}

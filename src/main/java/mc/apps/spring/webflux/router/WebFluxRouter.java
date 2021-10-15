package mc.apps.spring.webflux.router;

import mc.apps.spring.webflux.model.Todo;
import mc.apps.spring.webflux.model.TodoReactiveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class WebFluxRouter {
    private static final Logger logger = LogManager.getLogger(WebFluxRouter.class);

    final
    TodoReactiveService todoService;
    public WebFluxRouter(TodoReactiveService todoService) {
        this.todoService = todoService;
    }

    @Bean
    public RouterFunction<ServerResponse> simpleRoute (WebFluxHandler webFluxHandler) {
        logger.info("simpleRoute..");
        return RouterFunctions.route(RequestPredicates
                        .GET("/")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), webFluxHandler::hello);
    }

    @Bean
    RouterFunction<ServerResponse> composedRoutes() {
        return
                route(
                        GET("/todos"),
                        req -> ok().body(todoService.findAll(), Todo.class)
                )
                .and(route(GET("/todos/{id}"),
                        req -> ok().body(
                                todoService.findById(Integer.valueOf(req.pathVariable("id"))),
                                Todo.class)
                        )
                )
                .and(route(POST("/todos/update"),
                        req -> req.body(toMono(Todo.class))
                                .doOnNext(todoService::update)
                                .then(ServerResponse.ok().build()))
                )
                .and(route(POST("/todos/create"),
                        req -> req.body(toMono(Todo.class))
                                .doOnNext(todoService::create)
                                .then(ok().build()))
                )
                .andRoute(RequestPredicates.POST("/todos/save"),
                    req -> ServerResponse.ok().body(todoService.create(req.bodyToMono(Todo.class)), Todo.class)
                );
    }


//    @Bean
//    public RouterFunction<ServerResponse> getTodosRoute() {
//        return route(GET("/todos"),  request -> ServerResponse.ok().body(todoService.findAll(), Todo.class));
//    }

//    @Bean
//    public RouterFunction<ServerResponse> routes() {
//
//        logger.info("routes..");
//        return RouterFunctions.nest(RequestPredicates.path("/api"),
//                route(GET("/todos"),
//                                req-> ServerResponse.ok().body(todoService.findAll(), Todo.class))
//                        .andRoute(GET("/todos/{title}"),
//                                req-> ServerResponse.ok().body(todoService.findByTitle(req.pathVariable("title")), Todo.class))
//                        .andRoute(GET("/todos/id/{id}"),
//                                req -> ServerResponse.ok().body(todoService.findById(Integer.parseInt(req.pathVariable("id"))), Todo.class))
//                        .andRoute(RequestPredicates.POST("/todos/create"),
//                                req -> req.body(req.bodyToMono(Todo.class))
//                                        .doOnNext(todoService.create(req.bodyToFlux(Todo.class)))
//                                        .then(ServerResponse.ok().build()))
////                                req -> ServerResponse.().body(todoService.create(req.bodyToFlux(Todo.class)), Todo.class))
////                        .andRoute(RequestPredicates.POST("/todos"),
////                                req -> ServerResponse.ok().body(todoService.saveAll(req.bodyToFlux(Todo.class)), Todo.class))
//                        .andRoute(RequestPredicates.DELETE("/todos/id/{id}"),
//                                req -> ServerResponse.ok().body(todoService.delete(Integer.parseInt(req.pathVariable("id"))), Void.class))
//        );
//    }
}

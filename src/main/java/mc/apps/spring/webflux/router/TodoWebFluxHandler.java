package mc.apps.spring.webflux.router;

import mc.apps.spring.webflux.model.Todo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TodoWebFluxHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromPublisher(Mono.just("Hello from Spring WebFlux!"), String.class));
                                    //.fromObject("Hello from Spring WebFlux!")); //deprecated
    }

    public Mono<ServerResponse> todo(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Todo("Spring Webflux!")));
    }
}

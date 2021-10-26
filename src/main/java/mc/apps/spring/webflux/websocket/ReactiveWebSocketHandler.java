package mc.apps.spring.webflux.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * handle WebSocket messages and lifecycle events.
 * This Handler will receive a message and return it prefixed with “Server Response : ”.
 */
@Component
public class ReactiveWebSocketHandler implements WebSocketHandler  {

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        Flux<WebSocketMessage> stringFlux = webSocketSession.receive()
                .map(msg -> "Server Response : " + msg.getPayloadAsText())
                .map(String::toUpperCase)
                .map(webSocketSession::textMessage);

        return webSocketSession.send(stringFlux);
    }

}

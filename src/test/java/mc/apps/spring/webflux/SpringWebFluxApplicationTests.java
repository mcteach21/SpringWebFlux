package mc.apps.spring.webflux;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.Duration;

@SpringBootTest
class SpringWebFluxApplicationTests {
    private static final Logger logger = LogManager.getLogger(SpringWebFluxApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    void WebsocketTests(){
        WebSocketClient client = new ReactorNettyWebSocketClient();
        URI uri = URI.create("ws://localhost:8080/hi");

        Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1));
        client.execute(uri, webSocketSession ->
                webSocketSession.send(
                        longFlux.map(i -> webSocketSession.textMessage("mc says : hello_" + i))
                ).and(
                        webSocketSession.receive()
                                .map(WebSocketMessage::getPayloadAsText)
                                .doOnNext(logger::info)
                ).then()
        ).block(Duration.ofSeconds(10));
    }

}

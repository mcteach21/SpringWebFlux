package mc.apps.spring.webflux;

import mc.apps.spring.webflux.webclient.ReactiveWebClient;
import mc.apps.spring.webflux.websocket.ReactiveWebSocketHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class SpringWebFluxApplication {
    private static final Logger logger = LogManager.getLogger(SpringWebFluxApplication.class);

//    @Autowired
//    private WebSocketHandler webSocketHandler;

    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxApplication.class, args);
        // cf . ReactiveWebClientTests
    }

//    @Bean
//    public ReactiveWebSocketHandler websocketHandler() {
//        return new ReactiveWebSocketHandler();
//    }
//
//    @Bean
//    public HandlerMapping handlerMapping() {
//        Map<String, WebSocketHandler> map = new HashMap<>();
//        map.put("/hello", websocketHandler());
//
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        mapping.setUrlMap(map);
//        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return mapping;
//    }
//
//    @Bean
//    public WebSocketHandlerAdapter handlerAdapter() {
//        return new WebSocketHandlerAdapter(webSocketService());
//    }
//
//    @Bean
//    public WebSocketService webSocketService() {
//        return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
//    }

}

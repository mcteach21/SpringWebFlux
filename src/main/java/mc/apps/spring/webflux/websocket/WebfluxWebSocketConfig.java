package mc.apps.spring.webflux.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebfluxWebSocketConfig {
    private static final Logger logger = LogManager.getLogger(WebfluxWebSocketConfig.class);

    @Autowired
    private WebSocketHandler handler;

    @Bean
    public HandlerMapping handlerMapping(){
        logger.info("WebfluxConfig - handlerMapping..");

        Map<String, WebSocketHandler> handlerMap = new HashMap<>();
        handlerMap.put("/hello", handler); //websocketHandler());
        handlerMap.put("/hi", handler); //websocketHandler());

        return new SimpleUrlHandlerMapping(handlerMap, Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter(webSocketService());
    }
    @Bean
    public WebSocketService webSocketService() {
        return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
    }
}

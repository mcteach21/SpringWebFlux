package mc.apps.spring.webflux.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

//@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE + 99)
//@EnableWebSocketMessageBroker               // permet la gestion des messages WebSocket
public class WebSocketWithStompMessagingConfig  {//implements WebSocketMessageBrokerConfigurer {

//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//
//        // enregistre point de terminaison (de secours)  SockJS  si WebSocket pas disponible
//        registry.addEndpoint("/stomp-messages-websocket").withSockJS();
//        //.setAllowedOrigins("mydomain.com")
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config){
//
//        // destinations (prefix) pour envoi et réception de messages clients
//        config.enableSimpleBroker("/topic/", "/queue/");
//
//        // filtrer les destinations gérées par les méthodes annotées avec @MessageMapping (dans Controller)
//        config.setApplicationDestinationPrefixes("/app");
//    }
}

package mc.apps.spring.webflux;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

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

package mc.apps.spring.webflux.webclient;

import mc.apps.spring.webflux.controllers.TodoRestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReactiveWebClientTests implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger(ReactiveWebClientTests.class);

    @Override
    public void run(String... args) throws Exception {
        ReactiveWebClient webClient = new ReactiveWebClient();

//      webClient.create();
//      webClient.find("WebClient");

        webClient.getAll();
    }
}

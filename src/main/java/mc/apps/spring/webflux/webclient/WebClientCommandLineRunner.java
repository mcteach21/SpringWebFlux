package mc.apps.spring.webflux.webclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WebClientCommandLineRunner implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger(WebClientCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        ReactiveWebClient webClient = new ReactiveWebClient();

//        webClient.create();
//        webClient.findByTitle("WebClient");
//        webClient.getAll();

        webClient.getTitle();
    }
}

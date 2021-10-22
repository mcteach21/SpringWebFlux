package mc.apps.spring.webflux;

import mc.apps.spring.webflux.model.Todo;
import mc.apps.spring.webflux.webclient.ReactiveWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import reactor.core.publisher.Flux;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class SpringWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxApplication.class, args);

        // cf . ReactiveWebClientTests
    }

}

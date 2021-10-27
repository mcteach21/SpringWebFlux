package mc.apps.spring.webflux.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class AdminAccessOnlyConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //        http.csrf().disable()
        //                .authorizeExchange()
        //                .pathMatchers(HttpMethod.GET, "/api/todos").hasRole("ADMIN")
        //                .pathMatchers("/**").permitAll()
        //                .and()
        //                .httpBasic();

        http.csrf().disable()
                .authorizeExchange()
                .pathMatchers("/api/todos")
                .authenticated()
                .pathMatchers("/**").permitAll()
                .and()
                .formLogin();
        return http.build();
    }
}

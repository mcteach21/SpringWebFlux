package mc.apps.spring.webflux.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
//

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

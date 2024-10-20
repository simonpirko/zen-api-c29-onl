package by.tms.zenapic29onl.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(e -> e
                        .requestMatchers("/test").permitAll() // Разрешаем доступ...
                        .anyRequest().authenticated()     // Все остальные запросы требуют аутентификации
                )
                .sessionManagement(session ->         // Настройка сессий
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

       // http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}

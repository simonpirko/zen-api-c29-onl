package by.tms.zenapic29onl.configuration;

import by.tms.zenapic29onl.filter.JwtRequestFilter;
import by.tms.zenapic29onl.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final ObjectMapper objectMapper;
    private final SecurityProperties restSecProps;
    private final JwtRequestFilter tokenAuthenticationFilter;

    public SecurityConfiguration(ObjectMapper objectMapper, SecurityProperties restSecProps, JwtRequestFilter tokenAuthenticationFilter) {
        this.objectMapper = objectMapper;
        this.restSecProps = restSecProps;
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(restSecProps.getAllowedOrigins());
        configuration.setAllowedMethods(restSecProps.getAllowedMethods());
        configuration.setAllowedHeaders(restSecProps.getAllowedHeaders());
        configuration.setAllowCredentials(restSecProps.getAllowCredentials());
        configuration.setExposedHeaders(restSecProps.getExposedHeaders());
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return (httpServletRequest, httpServletResponse, e) -> {
            Map<String, Object> errorObject = new HashMap<>();
            errorObject.put("message", e.getMessage());
            errorObject.put("error", HttpStatus.UNAUTHORIZED);
            errorObject.put("code", HttpStatus.UNAUTHORIZED.value());
            errorObject.put("timestamp", OffsetDateTime.now());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorObject));
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .exceptionHandling(e -> e.authenticationEntryPoint(restAuthenticationEntryPoint()))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a -> a.requestMatchers(HttpMethod.POST, "/api/user/**", "/api/auth/**").permitAll().requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                .addFilterAt(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}

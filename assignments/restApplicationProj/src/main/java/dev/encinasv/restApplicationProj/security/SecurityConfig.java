package dev.encinasv.restApplicationProj.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder encoder() { return new BCryptPasswordEncoder(); }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector intro) throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(intro);
         return http.authorizeHttpRequests(authorize -> authorize
//                                 .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/**")).permitAll()
//                                 .requestMatchers(mvc.).permitAll()
                                 .requestMatchers(mvc.pattern("/")).permitAll()
                                 .requestMatchers(mvc.pattern("/conversionRate")).permitAll()
                                 .requestMatchers(mvc.pattern("/conversion**")).permitAll()
                                 .requestMatchers(mvc.pattern("/conversionCalculator")).permitAll()
                                 .requestMatchers(mvc.pattern("/changeBaseCurrency")).permitAll()

//                                 .requestMatchers(mvc.pattern("/**")).permitAll()
//                         .requestMatchers(mvc.pattern("/api-v1/**")).permitAll()
                 )
                 .formLogin(form -> form.loginPage("/login").permitAll())
                 .build();
    }
}

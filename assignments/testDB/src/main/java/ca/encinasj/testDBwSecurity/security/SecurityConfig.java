package ca.encinasj.testDBwSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.http.MatcherType.mvc;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    MvcRequestMatcher.Builder mvc;

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
        return encoder;
    }
    @Bean
    InMemoryUserDetailsManager users(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("josh")
                        .password(passwordEncoder.encode("password"))
                        .roles("ADMIN")
                        .build()
        );
    }
//    @Bean
//    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
//        return new MvcRequestMatcher.Builder(introspector);
//    }

//    @Bean
//    @Order(2)
//    SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers(mvc.pattern( "/")).permitAll();
//                })
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(Customizer.withDefaults())
//                .build();
//    }

//    @Bean
//    @Order(2)
//    SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests( auth -> {
//                    auth.requestMatchers(antMatcher("/h2-console/**")).permitAll();
//                    auth.requestMatchers(mvc.pattern("/")).permitAll();
////                    auth.requestMatchers("/").permitAll();
////                    auth.requestMatchers("/secure/**").authenticated();
//                })
//                .csrf(csrf -> csrf.ignoringRequestMatchers(antMatcher("/h2-console/**")))
////                .formLogin(Customizer.withDefaults())
//                .headers(headers -> headers.frameOptions().disable())
//                .build();
//    }

//    @Order()
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers(mvc.pattern( "/")).permitAll();
//                })
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(Customizer.withDefaults())
//                .build();
//    }

//
//    @Bean
//    @Order(1)
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers(mvc.pattern("/")).permitAll();
////                    auth.anyRequest().authenticated();
//                })
//                .build();
//
//    }


    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize-> authorize.requestMatchers("/secure/**").hasRole("USER")
                        .requestMatchers("/", "/js/**", "/css/**", "/images/**", "/permission-denied").permitAll()
                        .requestMatchers("/**").denyAll())
                .formLogin(form-> form.loginPage("/login").permitAll())
                .exceptionHandling(exception-> exception.accessDeniedPage("/permission-denied"))
                .logout(logout-> logout.permitAll())
                .build();
    }




}

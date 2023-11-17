package ca.sheridancollege.encinasv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("1@1")
//                .password(passwordEncoder.encode("1"))
//                .roles("USER")
//                .build();
//        UserDetails guest = User.withUsername("guest@guest.com")
//                .password(passwordEncoder.encode("password"))
//                .roles("GUEST")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, guest);
//    }
//    @Bean
//    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(authorize->authorize
//                        .requestMatchers("/secure/**", "/h2-console/*").hasRole("USER")
//                        .requestMatchers("/", "/js/**", "/css/**", "/images/**", "/permission-denied").permitAll()
//                        .requestMatchers("/**").denyAll())
//                .formLogin(form-> form.loginPage("/login").permitAll())
//                .exceptionHandling(exception-> exception.accessDeniedPage("/permission-denied"))
//                .logout(logout-> logout.permitAll())
//                .build();

    // SecurityConfig.java
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector
            introspector) throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);
        return http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(mvc.pattern("/secure/**")).hasRole("USER")
                        .requestMatchers(mvc.pattern("/")).permitAll()
                        .requestMatchers(mvc.pattern("/js/**")).permitAll()
                        .requestMatchers(mvc.pattern("/css/**")).permitAll()
                        .requestMatchers(mvc.pattern("/images/**")).permitAll()
                        .requestMatchers(mvc.pattern("/permission-denied")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(mvc.pattern("/**")).denyAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).disable())
//                         .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
                         .headers(headers -> headers.frameOptions().disable())
                                 .formLogin(form -> form.loginPage("/login").permitAll())
                         .exceptionHandling(exception -> exception.accessDeniedPage("/permission-denied"))
                         .logout(logout -> logout.permitAll())
                         .build();
                         }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
////        PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        return encoder;
//    }



}

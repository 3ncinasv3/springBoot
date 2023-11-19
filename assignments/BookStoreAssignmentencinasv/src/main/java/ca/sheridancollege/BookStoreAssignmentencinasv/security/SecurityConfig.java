package ca.sheridancollege.BookStoreAssignmentencinasv.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
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
    @Primary
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector intro)
    throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(intro);
        return http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(mvc.pattern("/secure/**")).hasRole("USER")
                        .requestMatchers(mvc.pattern("/")).permitAll()
                        .requestMatchers(mvc.pattern("/gameOfThrones")).permitAll()
                        .requestMatchers(mvc.pattern("/details/**")).permitAll()
                        .requestMatchers(mvc.pattern("/js/**")).permitAll()
                        .requestMatchers(mvc.pattern("/css/**")).permitAll()
                        .requestMatchers(mvc.pattern("/images/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/register")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/register")).permitAll()
                        .requestMatchers(mvc.pattern("/permission-denied")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(mvc.pattern("/**")).denyAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).disable())
//                         .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
//                .headers(headers -> headers.frameOptions().disable())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                        .formLogin(form -> form.loginPage("/login").permitAll())
                        .formLogin(form -> form.loginPage("/login").permitAll())
//                        .formLogin(form -> form.loginPage("/").permitAll())
                        .exceptionHandling(exception -> exception.accessDeniedPage("/permission-denied"))
                        .logout(LogoutConfigurer::permitAll)
                        .build();


                    }
}


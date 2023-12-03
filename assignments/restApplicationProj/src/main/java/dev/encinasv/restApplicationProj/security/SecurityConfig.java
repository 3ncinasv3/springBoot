package dev.encinasv.restApplicationProj.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
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
                                 .requestMatchers(mvc.pattern("/")).permitAll()
                                 .requestMatchers(mvc.pattern("/conversionRate")).permitAll()
                                 .requestMatchers(mvc.pattern("/conversion**")).permitAll()
                                 .requestMatchers(mvc.pattern("/conversionCalculator")).permitAll()
                                 .requestMatchers(mvc.pattern("/changeBaseCurrency")).permitAll()
                                 .requestMatchers(mvc.pattern("/api-v1/get")).permitAll()
                                 .requestMatchers(mvc.pattern("/stocks")).permitAll()
                                 .requestMatchers(mvc.pattern("/register")).permitAll()
                                 .requestMatchers(mvc.pattern("/css/**")).permitAll()
                                 .requestMatchers(mvc.pattern("/js/**")).permitAll()
                                 .requestMatchers(mvc.pattern("/static/css/**")).permitAll()
                                 .requestMatchers(mvc.pattern("/static/js/**")).permitAll()

                                 .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/register")).permitAll()
                                 .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()

                                 .anyRequest().authenticated()

//                                 .requestMatchers(mvc.pattern("/**")).permitAll()
//                         .requestMatchers(mvc.pattern("/api-v1/**")).permitAll()
                 )
                 .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console")).disable())
                 .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                 .formLogin(Customizer.withDefaults())
                 .oauth2Login(Customizer.withDefaults())
                 .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/"))
                 .build();
    }
}

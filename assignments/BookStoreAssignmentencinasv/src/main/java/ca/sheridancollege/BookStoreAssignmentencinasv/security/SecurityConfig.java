package ca.sheridancollege.BookStoreAssignmentencinasv.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

//    @Bean
//    @Primary
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector intro)
//    throws Exception {
//        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(intro);
//        return http.authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(mvc.pattern("/secure/**")).hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(mvc.pattern("/admin/**")).hasRole("ADMIN")
//                        .requestMatchers(mvc.pattern("/")).permitAll()
//                        .requestMatchers(mvc.pattern("/gameOfThrones")).permitAll()
//                        .requestMatchers(mvc.pattern("/details/**")).permitAll()
//                        .requestMatchers(mvc.pattern("/js/**")).permitAll()
//                        .requestMatchers(mvc.pattern("/css/**")).permitAll()
//                        .requestMatchers(mvc.pattern("/images/**")).permitAll()
//                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/register")).permitAll()
//                        .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/register")).permitAll()
//                        .requestMatchers(mvc.pattern("/permission-denied")).permitAll()
//                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
////                        .requestMatchers(mvc.pattern("/**")).denyAll()
//                )
//                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).disable())
//                .sessionManagement(httpSecuritySessionManagementConfigurer ->
//                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                        .formLogin(form -> form.loginPage("/login").permitAll())
////                        .oauth2Login(Customizer.withDefaults())
//                        .exceptionHandling(exception -> exception.accessDeniedPage("/permission-denied"))
////                        .logout(LogoutConfigurer::permitAll)
//                        .logout(form -> form.logoutSuccessUrl("/"))
////                        .logout()
//                        .build();
//                    }
@Bean
@Primary
public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector intro)
        throws Exception {
    MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(intro);
    return http.authorizeHttpRequests(authorize -> authorize
                            .requestMatchers(mvc.pattern("/")).permitAll()
                            .requestMatchers(mvc.pattern("/details/**")).permitAll()
                            .requestMatchers(mvc.pattern("/gameOfThrones")).permitAll()
                            .requestMatchers(mvc.pattern("/js/**")).permitAll()
                            .requestMatchers(mvc.pattern("/css/**")).permitAll()
                            .requestMatchers(mvc.pattern("/images/**")).permitAll()
                            .requestMatchers(mvc.pattern("/secure/**")).hasAnyRole("USER", "ADMIN")
                            .requestMatchers(mvc.pattern("/admin/**")).hasRole("ADMIN")

                            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/register")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/register")).permitAll()
                            .requestMatchers(mvc.pattern("/permission-denied")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
//                        .requestMatchers(mvc.pattern("/**")).denyAll()
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).disable())
            .sessionManagement(httpSecuritySessionManagementConfigurer ->
                    httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .formLogin(form -> form.loginPage("/login").permitAll())
//                        .oauth2Login(Customizer.withDefaults())
            .exceptionHandling(exception -> exception.accessDeniedPage("/permission-denied"))
//                        .logout(LogoutConfigurer::permitAll)
            .logout(form -> form.logoutSuccessUrl("/"))
//                        .logout()
            .build();
}
}


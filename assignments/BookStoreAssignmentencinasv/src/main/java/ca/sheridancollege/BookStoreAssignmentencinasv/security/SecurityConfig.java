package ca.sheridancollege.BookStoreAssignmentencinasv.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@EnableWebSecurity
@SuppressWarnings("All")
@Configuration
public class SecurityConfig {
//    @Autowired
//    final
//    DatabaseAccess da;
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
                .headers(headers -> headers.frameOptions().disable())
                        .formLogin(form -> form.loginPage("/login").permitAll())
                        .exceptionHandling(exception -> exception.accessDeniedPage("/permission-denied"))
                        .logout(LogoutConfigurer::permitAll)
                        .build();
                    }
}


//    @Autowired
//    MvcRequestMatcher.Builder mvc;

//    public SecurityConfig(DatabaseAccess da) {
//        this.da = da;
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource((DataSource) da)
//                .withDefaultSchema()
//                .withUser(User.withUsername("user")
//                        .password(passwordEncoder().encode("pass"))
//                        .roles("USER"));
//    }


//    public SecurityConfig(DatabaseAccess da) {
//        this.da = da;
//    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService (PasswordEncoder passwordEncoder) {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        for (long i = 1; i <= da.getCustomerListSize();i++) {
//            manager.createUser(User.withUsername(da.getCustomerEmailByID(i))
//                    .password(passwordEncoder
//                            .encode(da.getCustomerPassByID((i))))
//                    .roles("USER")
//                    .build());
//        }
//        UserDetails admin = User.withUsername("encinasv@sheridancollege.ca")
//                .password(passwordEncoder().encode("12345"))
//                .roles("ADMIN")
//                .build();
//
//        manager.createUser(admin);
//
//        return manager;
//    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//    }

//    @Bean
//    Builder mvc(HandlerMappingIntrospector introspector) {
//        return new Builder(introspector);
//    }


//    @Bean
//    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
//        RequestMatcher h2 = new AntPathRequestMatcher("/h2-console/**");
//
//        http
//                .authorizeRequests(authorize-> authorize
//                        .requestMatchers(h2).permitAll()
//                        .requestMatchers("/").permitAll()
//                        .requestMatchers("/secure/").hasRole("USER")
//                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
//                        .requestMatchers("/js/**", "/css/**", "/images/**", "/permission-denied").permitAll()
//                        .requestMatchers("/**").denyAll())
//                .formLogin(form-> form.loginPage("/login").permitAll())
//                .exceptionHandling(exception-> exception.accessDeniedPage("/permission-denied"))
//                .logout(logout-> logout.permitAll());
//
//        return http.build();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
//        RequestMatcher h2 = new AntPathRequestMatcher("/h2-console/**");
//        RequestMatcher index = new MvcRequestMatcher(introspector,("/"));
//        RequestMatcher secure = new MvcRequestMatcher(introspector,("/secure/**"));
//
//        http
//                .authorizeRequests(authorize -> {
//                    authorize
//                            .requestMatchers(h2).permitAll()
//                            .requestMatchers(index).permitAll()
//                            .requestMatchers(secure).hasAnyRole("USER", "ADMIN");
//                })
//                .formLogin(form-> form.loginPage("/login").defaultSuccessUrl("/secure/index"))
//                .exceptionHandling(exception-> exception.accessDeniedPage("/permission-denied"))
//                .logout(logout-> logout.permitAll().logoutSuccessUrl("/").invalidateHttpSession(false)
//                        .clearAuthentication(true));// Use default login form
//
//        // Disable CSRF and frame options to allow H2 Console to work
////        http.csrf().disable();
////        http.headers().disable();
//
//        return http.build();
//    }


//    @Bean
//    WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring()
//                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
//    }

//@Bean
//public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
//    return http
//            .authorizeRequests(authorize -> authorize
//                    .antMatchers("/h2-console/**").permitAll()
//                    .antMatchers("/js/**", "/css/**", "/images/**", "/permission-denied").permitAll()
//                    .antMatchers("/**").denyAll()
//            )
//            .formLogin(form -> form.loginPage("/login").permitAll())
//            .exceptionHandling(exception -> exception.accessDeniedPage("/permission-denied"))
//            .logout(logout -> logout.permitAll())
//            .csrf().disable()
//            .headers().frameOptions().disable()
//            .and()
//            .build();
//}



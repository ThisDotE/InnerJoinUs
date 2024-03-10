package org.thisdote.innerjoinus.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thisdote.innerjoinus.user.query.service.UserQueryService;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private UserQueryService userQueryService;
    private Environment environment;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtUtil jwtUtil;

    public WebSecurity(
            UserQueryService userQueryService,
            Environment environment,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userQueryService = userQueryService;
        this.environment = environment;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userQueryService).passwordEncoder(bCryptPasswordEncoder);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        httpSecurity.csrf((csrf) -> csrf.disable());
        httpSecurity.authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers(new AntPathRequestMatcher("/user/**")).permitAll()
//                .requestMatchers(new AntPathRequestMatcher("/user")).permitAll()
//                .requestMatchers(new AntPathRequestMatcher("/user", "POST")).permitAll()
//                .requestMatchers(new AntPathRequestMatcher("/user/**", "GET")).permitAll()
//                .requestMatchers(new AntPathRequestMatcher("/create", "POST")).permitAll()
                .requestMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
        )
                .authenticationManager(authenticationManager)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilter(getAuthenticationFilter(authenticationManager));
        httpSecurity.addFilterBefore(
                new JwtFilter(userQueryService, jwtUtil),
                UsernamePasswordAuthenticationFilter.class
        );

        return httpSecurity.build();
    }

    private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        return new AuthenticationFilter(authenticationManager, userQueryService, environment);
    }
}

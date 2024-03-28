package org.thisdote.authclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.thisdote.authclient.common.Constant.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .exposedHeaders(OAUTH_RESULT_TOKEN_KEY, CORS_EXPOSED_HEADER_SET_COOKIE, CORS_EXPOSED_HEADER_COOKIE)
                .allowedOrigins("*");
    }
}

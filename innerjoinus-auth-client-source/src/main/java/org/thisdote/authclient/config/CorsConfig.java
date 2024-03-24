package org.thisdote.authclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.thisdote.authclient.common.Constant.CORS_ALLOWED_ORIGIN;
import static org.thisdote.authclient.common.Constant.CORS_EXPOSED_HEADER;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders(CORS_EXPOSED_HEADER)
                .allowedOrigins(CORS_ALLOWED_ORIGIN);
    }
}

package ro.fmi.rpg.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ro.fmi.rpg.interceptor.JWTInterceptor;
import ro.fmi.rpg.interceptor.LoggerInterceptor;

/**
 * Created by User on 11/12/2016.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Autowired
    private LoggerInterceptor loggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor).addPathPatterns("/**");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/achievements");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/categories");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/tasks/**");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/user");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/friends/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("PUT", "DELETE", "OPTIONS", "GET", "POST");
    }

}

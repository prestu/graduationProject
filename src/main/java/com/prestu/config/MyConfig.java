package com.prestu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/user/one_set").setViewName("one_set");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/person/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/css/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOrigins("*")
        .allowCredentials(true)
        .allowedMethods("GET","POST","PUT","DELETE")
        .maxAge(3600);
    }
}

package com.example.springbootbill.config;

import com.example.springbootbill.Interceptor.LoginHandleInterceptor;
import com.example.springbootbill.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MySpringMvcConfigurer {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        //返回一个匿名内部类，重写其中的addViewControllers方法
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("main/login");
                registry.addViewController("/index.html").setViewName("main/login");
                registry.addViewController("/main.html").setViewName("main/index");
                registry.addViewController("/provider.html").setViewName("provider/list");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index.html"
                        ,"/css/**","/js/**","/img/**","/userLogin");
            }
        };
    }




    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}

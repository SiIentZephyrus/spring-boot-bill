package com.example.springbootbill.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer(){
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                //开启驼峰命名方式
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}

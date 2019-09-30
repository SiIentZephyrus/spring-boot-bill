package com.example.springbootbill.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    //绑定数据源配置
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /*@Bean
    @Primary
    public DataSource druid() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/jdbc?serverTimezone=GMT%2B8");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setFilters("stat");
        return dataSource;
    }*/

    /**
     * 配置Druid监控
     * 1.配置一个管理后台的servlet
     * 2.配置一个监控的filter
     *
     * */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String,String> initParam = new HashMap<>();
        //访问监控网站的用户名和密码
        initParam.put(StatViewServlet.PARAM_NAME_USERNAME,"root");
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD,"123");
        //允许访问的ip，默认所有ip访问
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW,"");
        //禁止对应的Ip访问
        initParam.put(StatViewServlet.PARAM_NAME_DENY,"192.168.11.111");


        bean.setInitParameters(initParam);
        return bean;

    }

    //配置一个监控的filter
    @Bean
    public FilterRegistrationBean filter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParam = new HashMap<>();
        //排除请求
        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/*");
        bean.setInitParameters(initParam);
        //拦截所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}

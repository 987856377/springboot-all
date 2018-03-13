package com.example.demo.configuration;

import com.alibaba.druid.support.http.StatViewFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by daier on 2018/2/24.
 */

    //要访问Druid的监控界面，访问地址：127.0.0.1:8080/druid/login.html
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean statViewServlet(){
//        创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
//        设置IP白名单
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
//        设置IP黑名单
        servletRegistrationBean.addInitParameter("deny","192.168.0.105");
//        设置控制台管理员账户
        servletRegistrationBean.addInitParameter("loginUsername","druid");
        servletRegistrationBean.addInitParameter("loginPassword","root");
//        设置是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable","true");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter(){
//        创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new StatViewFilter());
//        设置过滤器路径
        filterRegistrationBean.addUrlPatterns("/");
//        忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}

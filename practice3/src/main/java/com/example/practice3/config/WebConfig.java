package com.example.practice3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xslt.XsltView;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;
@Configuration
public class WebConfig {

    @Bean
    public ViewResolver xsltViewResolver(){
        XsltViewResolver viewResolver = new XsltViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setViewClass(XsltView.class);
        viewResolver.setViewNames("bookXSL", "userXSL");
        viewResolver.setPrefix("classpath:/xsl/");
        viewResolver.setSuffix(".xsl");
        return viewResolver;
    }
}

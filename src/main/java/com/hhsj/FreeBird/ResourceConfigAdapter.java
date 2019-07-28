package com.hhsj.FreeBird;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2019/7/20.
 */

@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/attached/**").addResourceLocations("file:/Users/XiaoDu/Desktop/FreeBird/src/main/resources/static/attached/");
    }

}

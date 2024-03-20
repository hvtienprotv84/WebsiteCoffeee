package com.tynuser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String productDir = "product-images";
        Path productPath = Paths.get(productDir);
        String productAbsolutePath = productPath.toFile().getAbsolutePath();

        registry.addResourceHandler("/" + productDir + "/**").addResourceLocations("file:/" + productAbsolutePath + "/");

        String categoryDir = "category-images";


    }
}

package com.tynadmin.config;

import com.tynadmin.util.RootPathImageUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        Path adminImagesDir = Paths.get(RootPathImageUtils.ADMIN);
        String  adminImagePath = adminImagesDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/" + RootPathImageUtils.ADMIN + "/**").addResourceLocations("file:/" + adminImagePath + "/");

        Path categoryImageDir = Paths.get(RootPathImageUtils.CATEGORY);
        String  categoryImagePath = categoryImageDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/" + RootPathImageUtils.CATEGORY + "/**").addResourceLocations("file:/" + categoryImagePath + "/");

        Path productImageDir = Paths.get(RootPathImageUtils.PRODUCT);
        String  productImagePath = productImageDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/" + RootPathImageUtils.PRODUCT + "/**").addResourceLocations("file:/" + productImagePath + "/");
    }
}

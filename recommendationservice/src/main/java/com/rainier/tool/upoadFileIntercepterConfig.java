package com.rainier.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: upoadFileIntercepterConfig
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/9/518:09
 */
//@Configuration
@Component
public class upoadFileIntercepterConfig extends WebMvcConfigurerAdapter {
    @Value("${filePath.uploads}")
    private  String filePath;
    @Value("${fileUrl.uploads}")
    private  String fileUrl;

    /**
     *
     * @Title:  文件上传路径映射
     * @Description:储存在  /recommendFile/** 路径下的文件映射到/home/recommendFile/
     *
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("/recommendFile/**").addResourceLocations("file:"+filePath);
        super.addResourceHandlers(registry);
    }

}



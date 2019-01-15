package com.lynn.boot;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.lynn.boot.bean.Person;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurationSupport {

//    @Override
//    public void customize(ConfigurableServletWebServerFactory factory) {
////        factory.setPort(8888);
//    }

    @Bean
    public Person person(){
        Person person = new Person();
        person.setName("lynn");
        return person;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat
        );
        List<MediaType> mediaTypeList = new ArrayList<>();
        //设置编码为UTF-8
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(mediaTypeList);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }


}

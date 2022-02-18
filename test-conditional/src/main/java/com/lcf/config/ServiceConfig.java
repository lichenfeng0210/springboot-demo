package com.lcf.config;

import com.lcf.modle.HeService;
import com.lcf.modle.MyService;
import org.springframework.context.annotation.*;

/**
 * @author lcf
 * @create 2021-12-29 14:59
 */
@Configuration
public class ServiceConfig {

    /*@Bean
    public HerService herService(){
        return new HerService();
    }*/

    @Bean
    public MyService myService(){
        return new MyService();
    }


    @Bean
    public HeService heService(){
        return new HeService();
    }

}

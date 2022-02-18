package com.lcf;

import com.lcf.config.ServiceConfig;
import com.lcf.modle.HerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lcf
 * @create 2021-12-29 14:58
 */
public class Application {
    /*
    通过 import注解 将对象注入到spring容器中
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfig.class);
        HerService herService = context.getBean(HerService.class);
        System.out.println(herService);
        System.out.println("second---");
        System.out.println("777");
        System.out.println("666");
    }
}

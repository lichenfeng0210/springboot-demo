package com.lcf;

import com.lcf.config.ServiceConfig;
import com.lcf.modle.HeService;
import com.lcf.modle.MyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author lcf
 * @create 2021-12-29 14:58
 */
@Component
public class Application {
    /*
    conditional 注解 当A对象注入到soring容器中时，将B，C对象也注入到spring容器中
    反之不注入
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ServiceConfig.class);
        /*HerService herService = context.getBean(HerService.class);
        System.out.println(herService);*/
        HeService heService = context.getBean(HeService.class);
        System.out.println(heService);
        MyService myService = context.getBean(MyService.class);
        System.out.println(myService);
    }
}

package com.lcf.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author lcf
 * @create 2021-12-29 15:10
 */
public class ServiceCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        boolean flag = conditionContext.getRegistry().containsBeanDefinition("HerService");
        return flag;
    }
}

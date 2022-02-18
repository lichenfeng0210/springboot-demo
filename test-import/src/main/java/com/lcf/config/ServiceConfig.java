package com.lcf.config;

import com.lcf.modle.HerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lcf
 * @create 2021-12-29 14:59
 */
@Configuration
@Import(value = {HerService.class})
public class ServiceConfig {
}

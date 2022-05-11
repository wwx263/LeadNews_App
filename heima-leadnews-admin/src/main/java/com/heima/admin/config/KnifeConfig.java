package com.heima.admin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.heima.common.knife4j")
/**
 * 通过创建配置类来启用common里面的knife4j
 */
public class KnifeConfig {
}
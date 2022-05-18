package com.heima.article.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.heima.common.exception")
/**
 * 配置类在启动之时会被加载
 * 而此处包扫描的地方就是common的包
 */
public class ExceptionCatchConfig {

}

package com.osc.oscms.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 通用配置类
 * 使用此配置类可以自动扫描common模块的组件
 */
@Configuration
@ComponentScan(basePackages = "com.osc.oscms.common")
public class CommonConfig {
}





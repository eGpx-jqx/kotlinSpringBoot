/*
 * ======================================
 * Spring Boot 应用启动类
 * ======================================
 * 描述：API 模块的 Spring Boot 应用程序入口点
 * 功能：配置和启动 Spring Boot 应用
 * 作者：开发团队
 * 日期：2025-07-10
 */

package com.egpx.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Spring Boot 应用启动类
 *
 * @SpringBootApplication 注解包含了以下功能：
 * - @Configuration: 标识这是一个配置类
 * - @EnableAutoConfiguration: 启用 Spring Boot 的自动配置机制
 * - @ComponentScan: 自动扫描当前包及子包下的组件
 */
@SpringBootApplication
class ApiApplication

/**
 * 应用程序主入口点
 *
 * @param args 命令行参数
 */
fun main(args: Array<String>) {
    // 启动 Spring Boot 应用
    // runApplication 是 Kotlin 扩展函数，简化了应用启动代码
    runApplication<ApiApplication>(*args)
}

/*
 * ======================================
 * 测试控制器
 * ======================================
 * 描述：提供基础的 API 接口测试功能
 * 功能：包含健康检查、Hello World 等测试接口
 * 作者：开发团队
 * 日期：2025-07-10
 */

package com.egpx.example.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * 测试控制器类
 *
 * @RestController: 组合注解，包含 @Controller 和 @ResponseBody
 * @RequestMapping: 定义控制器的基础路径
 */
@RestController
@RequestMapping("/api/test")
class TestController {

    /**
     * Hello World 测试接口
     *
     * @return 返回简单的问候消息
     */
    @GetMapping("/hello")
    fun hello(): ResponseEntity<Map<String, Any>> {
        val response = mapOf(
            "message" to "Hello, World!",
            "timestamp" to LocalDateTime.now(),
            "status" to "success"
        )
        return ResponseEntity.ok(response)
    }

    /**
     * 健康检查接口
     *
     * @return 返回应用程序健康状态
     */
    @GetMapping("/health")
    fun health(): ResponseEntity<Map<String, Any>> {
        val response = mapOf(
            "status" to "UP",
            "timestamp" to LocalDateTime.now(),
            "service" to "API Service",
            "version" to "1.0.0"
        )
        return ResponseEntity.ok(response)
    }

    /**
     * 带参数的测试接口
     *
     * @param name 用户名参数
     * @return 返回个性化问候消息
     */
    @GetMapping("/greet")
    fun greet(@RequestParam(defaultValue = "Guest") name: String): ResponseEntity<Map<String, Any>> {
        val response = mapOf(
            "message" to "Hello, $name!",
            "timestamp" to LocalDateTime.now(),
            "requestedName" to name
        )
        return ResponseEntity.ok(response)
    }

    /**
     * POST 请求测试接口
     *
     * @param requestBody 请求体数据
     * @return 返回处理结果
     */
    @PostMapping("/echo")
    fun echo(@RequestBody requestBody: Map<String, Any>): ResponseEntity<Map<String, Any>> {
        val response = mapOf(
            "message" to "Echo successful",
            "timestamp" to LocalDateTime.now(),
            "receivedData" to requestBody,
            "dataSize" to requestBody.size
        )
        return ResponseEntity.ok(response)
    }

    /**
     * 路径变量测试接口
     *
     * @param id 路径中的 ID 参数
     * @return 返回包含 ID 的响应
     */
    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<Map<String, Any>> {
        val response = mapOf(
            "userId" to id,
            "message" to "User information retrieved",
            "timestamp" to LocalDateTime.now(),
            "userData" to mapOf(
                "id" to id,
                "name" to "User$id",
                "email" to "user$id@example.com"
            )
        )
        return ResponseEntity.ok(response)
    }
}

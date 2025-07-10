/*
 * ======================================
 * Kotlin Spring Boot 多模块项目根构建配置
 * ======================================
 * 描述：多模块 Spring Boot + Kotlin 项目的根级别 Gradle 构建配置
 * 功能：定义插件版本、配置子模块通用设置
 * 作者：开发团队
 * 日期：2025-07-10
 */

// ======================================
// 插件版本管理
// ======================================
// 在根项目中声明所有插件但不应用（apply false）
// 这样做的好处：
// 1. 统一管理插件版本，避免子模块版本不一致
// 2. 子模块可以直接使用插件而无需指定版本
// 3. 利用 Version Catalog（libs.versions.toml）集中管理版本号
plugins {
    // Spring Boot 插件：提供 Spring Boot 应用的构建、打包、运行功能
    alias(libs.plugins.spring.boot) apply false

    // Spring 依赖管理插件：自动管理 Spring 相关依赖的版本兼容性
    alias(libs.plugins.spring.dependency.management) apply false

    // Kotlin JVM 插件：支持 Kotlin 编译到 JVM 字节码
    alias(libs.plugins.kotlin.jvm) apply false

    // Kotlin Spring 插件：为 Spring 框架优化 Kotlin 类（如自动添加 open 关键字）
    alias(libs.plugins.kotlin.spring) apply false
}

// ======================================
// 子模块通用配置
// ======================================
// 为所有子模块应用通用的构建配置
// 这样可以避免在每个子模块中重复相同的配置
subprojects {

    // ------ 插件应用 ------
    // 为每个子模块应用必要的插件
    // 注意：这里使用字符串 ID 而不是别名，因为子模块中插件已经在根项目声明

    // 应用 Kotlin JVM 编译支持
    plugins.apply("org.jetbrains.kotlin.jvm")

    // 应用 Kotlin Spring 集成插件（自动处理 Spring 注解类的 final 问题）
    plugins.apply("org.jetbrains.kotlin.plugin.spring")

    // 应用 Spring Boot 插件（提供启动、打包等任务）
    plugins.apply("org.springframework.boot")

    // 应用 Spring 依赖管理插件（自动处理 Spring BOM）
    plugins.apply("io.spring.dependency-management")

    // ------ 仓库配置 ------
    // 配置依赖下载的 Maven 仓库
    repositories {
        // 本地 Maven 仓库：优先使用本地缓存，提高构建速度
        mavenLocal()

        // Maven 中央仓库：主要的公共依赖仓库
        mavenCentral()

        // 可选：添加其他仓库（根据需要启用）
        // maven("https://repo.spring.io/milestone")  // Spring 里程碑版本仓库
        // maven("https://repo.spring.io/snapshot")   // Spring 快照版本仓库
    }

    // ------ 通用项目配置 ------
    // 可以在这里添加所有子模块共享的配置
    // 例如：编码格式、编译选项等

    // 设置项目编码为 UTF-8
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    // 配置源代码和文档的编码
    tasks.withType<Javadoc> {
        options.encoding = "UTF-8"
    }
}
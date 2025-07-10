/*
 * ======================================
 * API 模块构建配置文件
 * ======================================
 * 描述：Spring Boot + Kotlin API 服务模块的 Gradle 构建配置
 * 作者：开发团队
 * 日期：2025-07-10
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// ======================================
// 项目基本信息配置
// ======================================
group = "com.example.kot"
version = "1.0.0"

// ======================================
// 依赖管理配置
// ======================================
// 导入 Spring Boot 和 Spring Cloud 的 BOM (Bill of Materials)
// BOM 的作用：统一管理依赖版本，确保所有 Spring 相关依赖的版本兼容性
// 使用 BOM 后，在声明 Spring 相关依赖时无需指定具体版本号
dependencyManagement {
    imports {
        // Spring Boot BOM：管理 Spring Boot 核心依赖版本
        mavenBom(libs.spring.boot.bom.get().toString())
        // Spring Cloud BOM：管理 Spring Cloud 微服务依赖版本
        mavenBom(libs.spring.cloud.bom.get().toString())
    }
}

// ======================================
// 项目依赖声明
// ======================================
dependencies {
    // ------ Kotlin 核心依赖 ------
    // Kotlin 反射库：支持运行时类型检查和动态调用
    implementation(libs.kotlin.reflect)
    // Kotlin 标准库：提供 Kotlin 语言的基础功能
    implementation(libs.kotlin.stdlib)

    // ------ Spring Boot 核心依赖 ------
    // Spring Boot Web Starter：提供 Web 开发所需的核心组件
    // 包含：Spring MVC、Tomcat、Jackson、Validation 等
    implementation(libs.spring.boot.starter.web)

    // ------ 测试依赖 ------
    // Spring Boot 测试启动器：提供测试所需的依赖和配置
    // 包含：JUnit 5、Mockito、Spring Test、TestContainers 等
    testImplementation(libs.spring.boot.starter.test)

    // ------ 开发工具依赖 ------
    // Lombok：通过注解自动生成 getter/setter、构造函数等样板代码
    // 注意：在 Kotlin 项目中 Lombok 的使用较少，可以考虑移除
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

// ======================================
// Java 版本配置
// ======================================
java {
    // 设置源代码兼容的 Java 版本
    // 注意：Java 24 是预览版本，生产环境建议使用 LTS 版本（如 Java 21）
    sourceCompatibility = JavaVersion.VERSION_24
}

// ======================================
// Kotlin 编译器配置
// ======================================
// 配置 Kotlin 编译任务的参数
tasks.withType<KotlinCompile> {
    compilerOptions {
        // 设置 Kotlin 编译的目标 JVM 版本
        // 必须与上面的 sourceCompatibility 保持一致
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)

        // 添加编译器参数
        // -Xjsr305=strict：启用严格的 JSR-305 注解检查，提高空安全性
        freeCompilerArgs.add("-Xjsr305=strict")

        // 示例：添加其他编译器参数（根据需要启用）
        // freeCompilerArgs.addAll(listOf(
        //     "-Xcontext-receivers",  // 启用上下文接收器功能
        //     "-Xopt-in=kotlin.RequiresOptIn"  // 允许使用实验性 API
        // ))
    }
}

// ======================================
// 测试配置
// ======================================
// 配置测试任务使用 JUnit 5 平台
tasks.test {
    // 启用 JUnit Platform：支持 JUnit 5、Kotlin Test 等现代测试框架
    useJUnitPlatform()

    // 可选：配置测试 JVM 参数
    // jvmArgs("-XX:+EnableDynamicAgentLoading")

    // 可选：配置测试日志级别
    // testLogging {
    //     events("passed", "skipped", "failed")
    // }
}

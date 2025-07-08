import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// 关键：在所有子项目中导入 Spring Boot 和 Spring Cloud 的 BOM
// 这使得你在子模块中声明 Spring 相关依赖时无需指定版本
dependencyManagement {
    imports {
        mavenBom(libs.spring.boot.bom.get().toString())
        mavenBom(libs.spring.cloud.bom.get().toString())
    }
}

dependencies {
    // 为所有模块添加通用依赖
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation(libs.spring.boot.starter.web)
    // 使用 TOML 中定义的别名
    testImplementation(libs.spring.boot.starter.test)

    // 为所有模块添加 Lombok（如果需要）
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

group = "com.example.kot"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_24 // 指定 Java 版本
}

// 配置 Kotlin 编译器任务
tasks.withType<KotlinCompile> {
    compilerOptions {
        // jvmTarget 的新配置方式
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)

        // freeCompilerArgs 的新配置方式
        freeCompilerArgs.add("-Xjsr305=strict")

        // 如果有多个参数，可以这样添加
        // freeCompilerArgs.addAll("-Xcontext-receivers", "-Xanother-flag")
    }
}


tasks.test {
    useJUnitPlatform()
}






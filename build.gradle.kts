// 使用别名来应用插件
plugins {
    // `alias` 后跟的就是 TOML 文件中的插件别名
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.spring) apply false
}

// 为所有子项目配置通用信息
subprojects {

    plugins.apply("org.jetbrains.kotlin.jvm")
    plugins.apply("org.jetbrains.kotlin.plugin.spring")
    plugins.apply("org.springframework.boot")
    plugins.apply("io.spring.dependency-management")

    repositories {
        mavenCentral()
    }
}
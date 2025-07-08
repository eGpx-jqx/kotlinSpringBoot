pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

// 启用 Version Catalogs
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        // ... 其他仓库
    }
    // 默认会寻找 gradle/libs.versions.toml, 所以这行通常可以省略
    // versionCatalogs {
    //     create("libs") {
    //         from(files("gradle/libs.versions.toml"))
    //     }
    // }
}

rootProject.name = "kotlinSpringBoot"
include("api")
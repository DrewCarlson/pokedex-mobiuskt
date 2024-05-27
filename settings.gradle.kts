pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

rootProject.name = "Pokedex"
include(":android")
include(":desktop")
include(":shared")
include(":shared-ui")

plugins {
    alias(libs.plugins.androidkt) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.multiplatform) apply false
    alias(libs.plugins.serialization) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.apollo) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

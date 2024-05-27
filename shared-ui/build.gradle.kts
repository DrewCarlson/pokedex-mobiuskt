@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

dependencies {
    //kspCommonMainMetadata(libs.koin.ksp)
}

kotlin {
    jvmToolchain(17)
    applyDefaultHierarchyTemplate()
    jvm("desktop")

    androidTarget()

    wasmJs {
        moduleName = "pokedex"
        binaries.executable()
        browser {
            commonWebpackConfig {
                outputFileName = "pokedex.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                        add(project.projectDir.path + "/commonMain/")
                        add(project.projectDir.path + "/wasmJsMain/")
                    }
                }
            }
        }
    }
    configure(
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        )
    ) {
        binaries {
            framework {
                baseName = "pokedex"
                isStatic = true

                linkerOpts.add("-lsqlite3")

                export(libs.mobiuskt.core)
                export(libs.mobiuskt.coroutines)
                export(project(":shared"))
            }
        }
        afterEvaluate {
            tasks.named("embedAndSignAppleFrameworkForXcode").configure {
                // Framework is linked statically, this task will trigger the
                // correct build process but skip embedding and signing.
                enabled = false
            }
        }
    }

    /*configure(
        listOf(
            macosX64(),
            macosArm64()
        )
    ) {
        binaries {
            executable {
                entryPoint("pokedex.main")
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
                linkerOpts.add("-lsqlite3")
            }
        }
    }*/

    sourceSets {
        all {
            languageSettings {
                optIn("kt.mobius.compose.ExperimentalMobiusktComposeApi")
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                optIn("androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi")
            }
        }
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(libs.kotlin.stdlib)
                api(project(":shared"))

                // Compose
                api(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)

                // Koin
                // api(libs.koin.annotations)
                api(libs.koin.core)
                api(libs.koin.compose)
                implementation(libs.stately)

                // KotlinX Serialization Json
                implementation(libs.serialization.json)

                // Coroutines
                implementation(libs.coroutines.core)

                api(libs.mobiuskt.core)
                api(libs.mobiuskt.coroutines)
                implementation(libs.mobiuskt.extras)
                implementation(libs.mobiuskt.compose)

                implementation(libs.coil)
                implementation(libs.coil.compose)
                implementation(libs.coil.network.ktor.get())
                api(libs.precompose)
                api(libs.precompose.viewmodel)

                implementation(libs.material.sizeclass)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            if (name != "kspCommonMainKotlinMetadata") {
                dependsOn("kspCommonMainKotlinMetadata")
            }
        }
    }
}

configurations.all {
    if (name.startsWith("wasmJs")) {
        resolutionStrategy.eachDependency {
            if (requested.group.startsWith("io.ktor") &&
                requested.name.startsWith("ktor-client-")) {
                useVersion(libs.versions.ktorio.get())
            }
        }
    }
}

android {
    namespace = "pokedex.shared.ui"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

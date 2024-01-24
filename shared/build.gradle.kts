import com.mocoding.pokedex.Configuration

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
    id("app.cash.sqldelight")
    alias(libs.plugins.ksp)
}

compose {
    kotlinCompilerPlugin.set(libs.versions.composejbCompiler.get())
}

dependencies {
    kspCommonMainMetadata(libs.mobiuskt.codegen)
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    applyDefaultHierarchyTemplate()
    jvm("desktop")

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { _ ->
        cocoapods {
            summary = "Pokedex the Shared Module"
            homepage = "Link to the Shared Module homepage"
            version = "1.0.0"
            ios.deploymentTarget = "14.1"
            podfile = project.file("../ios/Podfile")
            framework {
                baseName = "shared"
                isStatic = true
            }
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("kt.mobius.gen.ExperimentalCodegenApi")
                optIn("kt.mobius.compose.ExperimentalMobiusktComposeApi")
            }
        }
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                // Compose
                with(compose) {
                    api(runtime)
                    api(foundation)
                    api(material)
                    api(material3)
                    api(materialIconsExtended)
                }

                // Ktor
                api(libs.ktor.client.core)
                api(libs.ktor.client.contentNegotiation)
                api(libs.ktor.client.logging)
                api(libs.ktor.serialization)

                // SqlDelight
                implementation(libs.sqldelight.coroutinesExtensions)
                implementation(libs.sqldelight.primitiveAdapters)

                // Koin
                api(libs.koin.core)
                api(libs.koin.compose)

                // KotlinX Serialization Json
                implementation(libs.serialization.json)

                // Coroutines
                implementation(libs.coroutines.core)

                api(libs.mobiuskt.core)
                api(libs.mobiuskt.coroutines)
                implementation(libs.mobiuskt.extras)
                implementation(libs.mobiuskt.codegen.api)
                implementation(libs.mobiuskt.compose)

                implementation(libs.coil)
                implementation(libs.coil.compose)
                implementation(libs.coil.network.ktor)

                api(libs.precompose)
                api(libs.precompose.viewmodel)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                // Ktor
                implementation(libs.ktor.client.okhttp)

                // SqlDelight
                implementation(libs.sqldelight.driver.android)

                // Koin
                implementation(libs.koin.android)
            }
        }
        val androidUnitTest by getting

        val desktopMain by getting {
            dependsOn(commonMain)

            dependencies {
                // Ktor
                implementation(libs.ktor.client.okhttp)

                // SqlDelight
                implementation(libs.sqldelight.driver.sqlite)
            }
        }

        val iosMain by getting {
            dependencies {
                // Ktor
                implementation(libs.ktor.client.darwin)

                // SqlDelight
                implementation(libs.sqldelight.driver.native)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}

android {
    namespace = "com.mocoding.pokedex"
    compileSdk = Configuration.compileSdk
    defaultConfig {
        minSdk = Configuration.minSdk
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

sqldelight {
    databases {
        create("PokemonDatabase") {
            packageName.set("com.mocoding.pokedex.core.database")
        }
    }
}

// For more details see: https://github.com/google/ksp/issues/567
if (tasks.any { it.name == "kspCommonMainKotlinMetadata" }) {
    tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
        if (name != "kspCommonMainKotlinMetadata") {
            dependsOn("kspCommonMainKotlinMetadata")
        }
    }
}

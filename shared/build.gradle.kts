plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.apollo)
}

dependencies {
    kspCommonMainMetadata(libs.mobiuskt.codegen)
    kspCommonMainMetadata(libs.koin.ksp)
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    applyDefaultHierarchyTemplate()

    jvmToolchain(17)

    jvm()
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    //macosX64()
    //macosArm64()

    sourceSets {
        all {
            languageSettings {
                optIn("kt.mobius.gen.ExperimentalCodegenApi")
                optIn("com.apollographql.apollo3.annotations.ApolloExperimental")
            }
        }
        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(libs.apollo.runtime)
                implementation(libs.apollo.caching)
                implementation(libs.apollo.ktor)
                // Ktor
                api(libs.ktor.client.core)
                api(libs.ktor.client.contentNegotiation)
                api(libs.ktor.client.logging)
                api(libs.ktor.serialization)

                // Koin
                api(libs.koin.core)
                implementation(libs.koin.annotations)
                implementation(libs.stately)

                // KotlinX Serialization Json
                implementation(libs.serialization.json)

                // Coroutines
                implementation(libs.coroutines.core)

                api(libs.mobiuskt.core)
                api(libs.mobiuskt.coroutines)
                implementation(libs.mobiuskt.extras)
                implementation(libs.mobiuskt.codegen.api)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
                implementation(libs.koin.android)
            }
        }
        val androidUnitTest by getting

        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }

        val appleMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        if (name != "kspCommonMainKotlinMetadata") {
            dependsOn("kspCommonMainKotlinMetadata")
        }
    }
}

android {
    namespace = "pokedex.shared"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

apollo {
    service("PokeApi") {
        packageName.set("pokedex.graphql")
        introspection {
            endpointUrl.set("https://beta.pokeapi.co/graphql/v1beta")
        }
    }
}

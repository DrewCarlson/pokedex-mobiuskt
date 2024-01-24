plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
    alias(libs.plugins.ksp)
}

dependencies {
    kspCommonMainMetadata(libs.koin.ksp)
}

compose {
    kotlinCompilerPlugin.set(libs.versions.composejbCompiler.get())
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    applyDefaultHierarchyTemplate()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }

    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
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

                freeCompilerArgs += listOf("-Xallocator=custom")
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
                api(libs.koin.annotations)
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
                implementation(libs.coil.network.ktor)

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
            kotlinOptions.jvmTarget = "17"
            if (name != "kspCommonMainKotlinMetadata") {
                dependsOn("kspCommonMainKotlinMetadata")
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
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

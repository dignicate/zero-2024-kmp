import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    kotlin("plugin.serialization") version "2.0.21"
    alias(libs.plugins.compose.compiler)
}

kotlin {
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
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation("androidx.core:core-splashscreen:1.0.1")

            implementation("com.google.accompanist:accompanist-pager:0.36.0")
            implementation("com.google.accompanist:accompanist-pager-indicators:0.36.0")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kotlinx.coroutines.core)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.logging)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime)
            implementation(libs.stately.common)
            // nav
            implementation(libs.navigation.compose)
            implementation(libs.screen.size)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
//            implementation(libs.mockito.core)
//            implementation(libs.mockito.kotlin)
//            implementation(libs.mockative)
            implementation(libs.junit)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.dignicate.zero_2024_kmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.dignicate.zero_2024_kmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
//    kotlinOptions {
//        jvmTarget = "17"
//    }
    dependencies {
        // Compose
        val composeBom = platform("androidx.compose:compose-bom:2025.02.00")
        implementation(composeBom)
        // Android Studio Preview support
        implementation(libs.compose.ui.tooling.preview)
        debugImplementation(libs.compose.ui.tooling)

        implementation(libs.kotlinx.coroutines.android)
    }
    buildFeatures {
        compose = true
    }
}
dependencies {
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.compose.material)

//    testImplementation(libs.kotlinx.coroutines.test)
//    testImplementation(libs.junit)

//    testImplementation("org.mockito:mockito-core:4.11.0")
//    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.dignicate.zero_2024_kmp"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}

tasks.register("assembleXCFramework") {
    group = "build"
    description = "Assembles a universal XCFramework for iOS"

    dependsOn(
        "linkReleaseFrameworkIosArm64",
        "linkReleaseFrameworkIosSimulatorArm64"
    )

    doLast {
        val outputDir = buildDir.resolve("XCFrameworks/release")
        val arm64Framework = buildDir.resolve("bin/iosArm64/releaseFramework/ComposeApp.framework")
        val simArm64Framework = buildDir.resolve("bin/iosSimulatorArm64/releaseFramework/ComposeApp.framework")

        outputDir.mkdirs()

        exec {
            commandLine = listOf(
                "xcodebuild", "-create-xcframework",
                "-framework", arm64Framework.absolutePath,
                "-framework", simArm64Framework.absolutePath,
                "-output", outputDir.resolve("ComposeApp.xcframework").absolutePath
            )
        }
    }
}

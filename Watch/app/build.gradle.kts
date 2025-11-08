    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.android)
    }

    android {
        namespace = "ro.pub.cs.systems.eim.firstapp.presentation"
        compileSdk = 35

        defaultConfig {
            applicationId = "ro.pub.cs.systems.eim.firstapp.presentation"
            minSdk = 24
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

        // Ktor Client (trimite cereri HTTP)
        implementation("io.ktor:ktor-client-core:2.3.7")
        implementation("io.ktor:ktor-client-cio:2.3.7")
        implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
        implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")

        // Compose, Wearable, etc (rămân neschimbate)
        implementation("androidx.activity:activity-compose:1.10.1")
        implementation("androidx.compose.ui:ui:1.6.5")
        implementation("androidx.compose.material3:material3:1.2.1")
        implementation(libs.play.services.wearable)

    }
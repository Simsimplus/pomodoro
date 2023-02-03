plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group "io.simsim"
version "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("com.google.android.material:material:1.8.0")
    implementation(files("libs/EasyFloat-2.0.4.aar"))
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "io.simsim.android"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
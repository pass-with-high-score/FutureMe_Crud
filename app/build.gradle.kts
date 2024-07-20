import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21"
}

android {
    namespace = "com.futureme.crud_supabase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.futureme.crud_supabase"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val supabaseApiKey: String =
            gradleLocalProperties(rootDir, providers).getProperty("API_KEY") ?: ""
        val supabaseUrl: String =
            gradleLocalProperties(rootDir, providers).getProperty("SUPABASE_URL") ?: ""
        val secret: String = gradleLocalProperties(rootDir, providers).getProperty("SECRET") ?: ""


        buildConfigField("String", "API_KEY", "\"$supabaseApiKey\"")
        buildConfigField("String", "SUPABASE_URL", "\"$supabaseUrl\"")
        buildConfigField("String", "SECRET", "\"$secret\"")
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.compose.material:material:1.6.8")

    implementation(platform("io.github.jan-tennert.supabase:bom:2.5.2"))
    implementation ("io.github.jan-tennert.supabase:gotrue-kt")
    implementation ("io.github.jan-tennert.supabase:postgrest-kt")
    implementation ("io.github.jan-tennert.supabase:storage-kt")

    implementation ("io.ktor:ktor-client-android:2.3.12")
    implementation ("io.ktor:ktor-utils:2.3.12")
    implementation ("io.ktor:ktor-client-core:2.3.12")
    implementation ("io.coil-kt:coil-compose:2.6.0")

    implementation ("com.google.dagger:hilt-android:2.51.1")
    annotationProcessor ("com.google.dagger:hilt-compiler:2.51.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    // For instrumentation tests
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.48")
    androidTestAnnotationProcessor ("com.google.dagger:hilt-compiler:2.51.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.48")
    implementation ("com.google.android.gms:play-services-auth:21.2.0")

    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.24.13-rc")
}
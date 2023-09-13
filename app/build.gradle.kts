plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.tanexc.siderakt"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.tanexc.siderakt"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.4.0"
        versionNameSuffix = "Andromeda"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += ("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-android-compiler:2.48")

    // Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui:1.6.0-alpha05")
    implementation("androidx.compose.material3:material3:1.2.0-alpha07")
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0-alpha07")
    implementation("androidx.compose.material:material-icons-core:1.6.0-alpha05")
    implementation("androidx.compose.material:material-icons-extended:1.6.0-alpha05")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha12")
    implementation("androidx.compose.foundation:foundation:1.6.0-alpha05")
    implementation("androidx.compose.ui:ui-util:1.6.0-alpha05")
    implementation("androidx.compose.material:material:1.5.1")

    // Coil
    implementation("io.coil-kt:coil:2.3.0")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("io.coil-kt:coil-gif:2.2.2")
    implementation("io.coil-kt:coil-svg:2.2.2")

    // Room
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")

    // Navigation
    implementation("dev.olshevski.navigation:reimagined:1.3.1")
    implementation("dev.olshevski.navigation:reimagined-hilt:1.3.1")

    // SplashScreenApi
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Datastore
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    // Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.29.1-alpha")
    implementation("com.google.accompanist:accompanist-flowlayout:0.29.1-alpha")
    implementation("com.google.accompanist:accompanist-placeholder-material:0.29.1-alpha")
    implementation("com.google.accompanist:accompanist-pager:0.29.1-alpha")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.29.1-alpha")

    // ComposeShadowPlus
    implementation("com.github.GIGAMOLE:ComposeShadowsPlus:1.0.2")
}

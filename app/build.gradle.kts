plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.example.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    // Dagger/Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // retrofit & json converter
    // val rfVers = "2.9.0"
    // implementation("com.squareup.retrofit2:retrofit:$rfVers")
    // implementation("com.squareup.retrofit2:converter-gson:$rfVers")

    // OkHttp
    // implementation("com.squareup.okhttp3:okhttp:4.12.0") // 5.0.0-alpha.2 in course

    // Coil
    // implementation("io.coil-kt:coil-compose:2.5.0")

    // Room
    // val roomVers = "2.6.1"
    // implementation("androidx.room:room-runtime:$roomVers")
    // annotationProcessor("androidx.room:room-compiler:$roomVers")
    // ksp("androidx.room:room-compiler:$roomVers")
    // implementation("androidx.room:room-ktx:$roomVers")

    // coroutines
    // val crVers = "1.7.3" // 1.5.2 in course
    // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${crVers}")
    // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${crVers}")
    // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${crVers}")
    // coroutine lifecycle scopes
    // implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0") //2.4.0

    // material icons
    // implementation("androidx.compose.material:material-icons-extended:1.5.4")



}
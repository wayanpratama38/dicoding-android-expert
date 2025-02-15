import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.foodist"
    compileSdk = 35

    val properties = Properties()
    properties.load(rootProject.file("local.properties").inputStream())

    defaultConfig {
        applicationId = "com.example.foodist"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String","API_KEY",properties["API_KEY"] as String)
        buildConfigField("String","API_HOST",properties["API_HOST"] as String)

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Network Dependency
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Coroutine Flow Dependency (Reactive Programming)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)


    implementation(libs.androidx.activity.ktx) //by viewModels()
    implementation(libs.androidx.lifecycle.runtime.ktx) //lifecycleScope
    implementation(libs.androidx.lifecycle.livedata.ktx) //asLiveData

    // Koin Dependency (Dependency Inject)
    implementation(libs.koin.android)

    // Room Dependency
    implementation(libs.androidx.room.runtime)
    ksp(libs.room.compiler)

}
plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}
android {
    namespace = "com.example.favorite"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
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
        viewBinding = true
    }
}

dependencies {
    implementation(project(":app"))
    implementation(project(":core"))
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    api(libs.androidx.activity)
    api(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    api(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.androidx.activity.ktx) //by viewModels()
    implementation(libs.androidx.lifecycle.runtime.ktx) //lifecycleScope
    implementation(libs.androidx.lifecycle.livedata.ktx) //asLiveData

    api(libs.recyclerview)
    api(libs.material)
    api(libs.glide)
    api(libs.koin.android)
}
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Hilt
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

    // Navigation - Safe Args
    alias(libs.plugins.navigation.safeargs.kotlin)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}
val newsApiKey = localProperties["NEWS_API_KEY"]

android {
    namespace = "com.aliozdemir.asparagas"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aliozdemir.asparagas"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "NEWS_API_KEY", "\"${newsApiKey}\"")
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
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

    // Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // OkHttp - Retrofit - Converter Gson - Gson
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.josh25.iristouchsecurenotes"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.josh25.iristouchsecurenotes"
        minSdk = 31
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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

    // Compose dependencies
    implementation(libs.composeUi)
    implementation(libs.composeMaterial)
    implementation(libs.composeTooling)
    debugImplementation(libs.composeToolingDebug)

    // Lifecycle and ViewModel for Compose
    implementation(libs.lifecycleViewModel)
    //implementation(libs.lifecycleRuntime)

    // Hilt dependency injection
    implementation(libs.hilt)
    ksp(libs.hiltCompiler)

    // Room for database
    implementation(libs.roomRuntime)
    ksp(libs.roomCompiler)

    // Biometric authentication
    implementation(libs.biometric)

    // Security - Android Keystore encryption
    implementation(libs.securityCrypto)

    // Coroutine dependencies (StateFlow)
    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)
}
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.scannerall_in_one"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.scannerall_in_one"
        minSdk = 28
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.ads)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation (libs.lottie)
    implementation (libs.cardview)
    implementation (libs.play.services.ads)
    // ML Kit Barcode Scanning (QR, UPI, Barcodes, etc.)
    implementation (libs.barcode.scanning)

// CameraX (used to access the device camera)
    implementation (libs.camera.core)
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.lifecycle)
    implementation (libs.androidx.camera.view)

    implementation ("com.google.guava:guava:31.1-android")




}
plugins {
    alias(libs.plugins.android.application)
}

android {
<<<<<<< HEAD
    namespace = "com.example.vanbaliem_2122110304"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.vanbaliem_2122110304"
        minSdk = 24
        targetSdk = 36
=======
    namespace = "com.example.hitcapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hitcapp"
        minSdk = 24
        targetSdk = 34
>>>>>>> 858203ee319f2506399132f5d3faf548896f9ebc
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
<<<<<<< HEAD
=======
    buildFeatures {
        viewBinding = true
    }
>>>>>>> 858203ee319f2506399132f5d3faf548896f9ebc
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
<<<<<<< HEAD
=======
    implementation(libs.annotation)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
>>>>>>> 858203ee319f2506399132f5d3faf548896f9ebc
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.android.volley:volley:1.2.1")
<<<<<<< HEAD
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0") // nếu dùng Java
    implementation("com.squareup.picasso:picasso:2.8")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
=======
>>>>>>> 858203ee319f2506399132f5d3faf548896f9ebc
    implementation("com.squareup.picasso:picasso:2.8")
// adding volley dependency
}
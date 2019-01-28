plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "kchaiko.vandrouki"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }
    signingConfigs {
        getByName("debug") {
            storeFile = file("vand_keystore.jks")
            storePassword = rootProject.extra["storePassword"] as String
            keyAlias = rootProject.extra["debugKeyAlias"] as String
            keyPassword = rootProject.extra["debugKeyPassword"] as String
        }
    }
    kapt {
        generateStubs = true
    }
}

dependencies {
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")
    testImplementation("junit:junit:4.12")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("androidx.palette:palette:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("org.jsoup:jsoup:1.11.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.11")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("org.koin:koin-android:1.0.2")
    implementation("org.koin:koin-androidx-viewmodel:1.0.2")
    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.github.bumptech.glide:glide:4.8.0")
    kapt("com.github.bumptech.glide:compiler:4.8.0")
    implementation("androidx.room:room-runtime:2.0.0")
    kapt("androidx.room:room-compiler:2.0.0")
    implementation("com.facebook.stetho:stetho:1.5.0")
    implementation("com.facebook.stetho:stetho-okhttp3:1.5.0")
    implementation("ru.terrakok.cicerone:cicerone:5.0.0")
    implementation("org.jetbrains.anko:anko-sdk25:0.10.8")
}
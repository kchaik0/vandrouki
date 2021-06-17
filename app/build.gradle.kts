plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "kchaiko.vandrouki"
        minSdk = 21
        targetSdk = 30
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
        kotlinCompilerExtensionVersion = "1.0.0-beta08"
    }
}

dependencies {
    //tests
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.koin:koin-test:1.0.2")

    //androidx
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.palette:palette-ktx:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.fragment:fragment-ktx:1.3.4")

    //jsoup
    implementation("org.jsoup:jsoup:1.13.1")

    //jetbrains kotlin, coroutines
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    //koin
    implementation("org.koin:koin-android:1.0.2")
    implementation("org.koin:koin-androidx-viewmodel:1.0.2")

    //glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")
    implementation("com.google.accompanist:accompanist-glide:0.11.1")

    //room
    implementation("androidx.room:room-runtime:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")

    //stetho
    implementation("com.facebook.stetho:stetho:1.5.1")
    implementation("com.facebook.stetho:stetho-okhttp3:1.5.1")

    //anko
    implementation("org.jetbrains.anko:anko-sdk25:0.10.8")

    //navigation component
    implementation("android.arch.navigation:navigation-fragment-ktx:1.0.0")

    //compose
    implementation("androidx.activity:activity-compose:1.3.0-beta01")
    implementation("androidx.compose.ui:ui:1.0.0-beta08")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta08")
    implementation("androidx.compose.material:material:1.0.0-beta08")
}
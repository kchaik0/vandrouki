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

object Versions {
    object Tests {
        const val espresso = "3.1.1"
        const val junit = "4.12"
        const val koin_test = "1.0.2"
    }
    const val appcompat = "1.0.2"
    const val cardview = "1.0.0"
    const val recyclerview = "1.0.0"
    const val palette = "1.0.0"
    const val constraintlayout = "1.1.3"
    const val core_ktx = "1.0.1"
    const val fragment_ktx = "1.1.0-alpha06"
    const val jsoup = "1.11.3"
    const val kotlin_stdlib = "1.3.21"
    const val kotlin_coroutines = "1.1.1"
    const val retrofit = "2.5.0"
    const val retrofit_coroutines = "0.9.2"
    const val koin = "1.0.2"
    const val glide = "4.8.0"
    const val room = "2.0.0"
    const val stetho = "1.5.0"
    const val anko = "0.10.8"
    const val navigation = "1.0.0"
}

dependencies {
    //tests
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.Tests.espresso}")
    testImplementation("junit:junit:${Versions.Tests.junit}")
    testImplementation("org.koin:koin-test:${Versions.Tests.koin_test}")

    //androidx
    implementation("androidx.appcompat:appcompat:${Versions.appcompat}")
    implementation("androidx.cardview:cardview:${Versions.cardview}")
    implementation("androidx.recyclerview:recyclerview:${Versions.recyclerview}")
    implementation("androidx.palette:palette:${Versions.palette}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}")
    implementation("androidx.core:core-ktx:${Versions.core_ktx}")
    implementation("androidx.fragment:fragment-ktx:${Versions.fragment_ktx}")

    //jsoup
    implementation("org.jsoup:jsoup:${Versions.jsoup}")

    //jetbrains kotlin, coroutines
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_stdlib}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit_coroutines}")

    //koin
    implementation("org.koin:koin-android:${Versions.koin}")
    implementation("org.koin:koin-androidx-viewmodel:${Versions.koin}")

    //glide
    implementation("com.github.bumptech.glide:glide:${Versions.glide}")
    kapt("com.github.bumptech.glide:compiler:${Versions.glide}")

    //room
    implementation("androidx.room:room-runtime:${Versions.room}")
    kapt("androidx.room:room-compiler:${Versions.room}")

    //stetho
    implementation("com.facebook.stetho:stetho:${Versions.stetho}")
    implementation("com.facebook.stetho:stetho-okhttp3:${Versions.stetho}")

    //anko
    implementation("org.jetbrains.anko:anko-sdk25:${Versions.anko}")

    //navigation component
    implementation("android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}")
}
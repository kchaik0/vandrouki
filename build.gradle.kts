buildscript {

    val storePassword by extra("79ppdb3g")
    val debugKeyAlias by extra("debug_key")
    val debugKeyPassword by extra("4tuab6sv")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta04")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
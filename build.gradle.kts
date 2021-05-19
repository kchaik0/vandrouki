buildscript {

    val composeVersion by extra("1.0.0-beta07")
    val storePassword by extra("79ppdb3g")
    val debugKeyAlias by extra("debug_key")
    val debugKeyPassword by extra("4tuab6sv")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
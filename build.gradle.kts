buildscript {

    rootProject.extra["storePassword"]      = "79ppdb3g"
    rootProject.extra["debugKeyAlias"]      = "debug_key"
    rootProject.extra["debugKeyPassword"]   = "4tuab6sv"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
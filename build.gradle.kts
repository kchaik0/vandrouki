buildscript {

    rootProject.extra["storePassword"]      = "79ppdb3g"
    rootProject.extra["debugKeyAlias"]      = "debug_key"
    rootProject.extra["debugKeyPassword"]   = "4tuab6sv"

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.70")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
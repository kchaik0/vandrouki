buildscript {

    rootProject.extra["storePassword"]      = "79ppdb3g"
    rootProject.extra["debugKeyAlias"]      = "debug_key"
    rootProject.extra["debugKeyPassword"]   = "4tuab6sv"

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.11")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

tasks.register<Wrapper>("wrapper") {
    gradleVersion = "4.6"
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
//val Versions.kotlin by extra("1.5.10")
    //    Versions.kotlin
//val Versions.kotlin by extra("1.5.10")
    //    ext.kotlin_version = "1.5.10"
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
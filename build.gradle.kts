import com.android.build.gradle.internal.dsl.decorator.SupportedPropertyType.Collection.List.type

// Top-level build file where you can add configuration options common to all sub-projects/modules.
// plugins { id("com.android.application") version "8.1.3" apply false id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false}

buildscript {

    repositories {
        google() //Google's Maven repository
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.4.2")
        classpath ("com.google.gms:google-services:4.4.0")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {

    }
}





plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.room)
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "org.piwigo.android"
        minSdk = 31
        targetSdk = 33
        versionCode = 103
        versionName = "1.0.3-dev"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        release {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
        // for view binding:
        // viewBinding = true
    }
    namespace = "org.piwigo"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.annotation)
    implementation(libs.android.material)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.lifecycle.viewmodel)
    annotationProcessor(libs.lifecycle.compiler)

    implementation(libs.dagger)
    annotationProcessor(libs.dagger.compiler)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    annotationProcessor(libs.dagger.android.processor)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.adapter.rxjava2)

    implementation(libs.picasso)
    implementation(libs.rx.android)
    implementation(libs.rx.java)
    implementation(libs.guava)
    annotationProcessor(libs.guava)

    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.room.rxjava2)
    implementation(libs.room.guava)


    implementation(libs.apache.lang3)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.vipulasri.ticketview)
    implementation(libs.androidx.preference)
    
    implementation(libs.fabprogresscircle)
    implementation(libs.speedDial)
    implementation(libs.snackprogressbar)
    implementation(libs.eventbus)
    implementation(libs.kotlin.stdlib.jdk8)
    /* Don't forget to add to string libraries if you add a library here. */

//    debugImplementation 'com.squareup.leakcanary:leakcanary-android:2.0-beta-3'

    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    testImplementation(libs.assertj)
    testAnnotationProcessor(libs.dagger.compiler)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.mockito.core)
    testImplementation(libs.guava)
    testImplementation(libs.androidx.appcompat)
    testAnnotationProcessor(libs.guava)
    testImplementation(libs.jsr305)

    androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.core.ktx)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.uiautomator)
    // Note that espresso-idling-resource is used in the code under test.
    implementation(libs.androidx.espresso.idling.resource)
}

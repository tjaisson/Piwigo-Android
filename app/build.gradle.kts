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
        // viewBinding true
    }
    namespace = "org.piwigo"
}

val picassoVersion = "2.8"
val assertjVersion = "1.2.0"
val espressoVersion = "3.3.0"

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
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

    implementation("com.squareup.picasso:picasso:${picassoVersion}")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.20")
    implementation(libs.guava)
    annotationProcessor(libs.guava)

    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(libs.room.rxjava2)
    implementation(libs.room.guava)


    implementation("org.apache.commons:commons-lang3:3.11")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.vipulasri:ticketview:1.0.7")
    implementation("androidx.preference:preference:1.1.1")
    
    implementation("com.github.jorgecastilloprz:fabprogresscircle:1.01@aar")
    implementation("com.leinardi.android:speed-dial:3.3.0")
    implementation("com.github.tingyik90:snackprogressbar:6.1.1")
    implementation("org.greenrobot:eventbus:3.2.0")
    implementation(libs.kotlin.stdlib.jdk8)
    /* Don't forget to add to string libraries if you add a library here. */

//    debugImplementation 'com.squareup.leakcanary:leakcanary-android:2.0-beta-3'

    testImplementation("junit:junit:4.13")
    testImplementation("org.robolectric:robolectric:4.4")
    testImplementation("com.squareup.assertj:assertj-android:${assertjVersion}")
    testAnnotationProcessor(libs.dagger.compiler)
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.mockito:mockito-core:3.5.13")
    testImplementation("com.google.guava:guava:29.0-android")
    testImplementation("androidx.appcompat:appcompat:1.2.0")
    testAnnotationProcessor("com.google.guava:guava:29.0-android")
    testImplementation("com.google.code.findbugs:jsr305:3.0.2")

    androidTestImplementation("androidx.test:core:1.4.0")
    androidTestImplementation("androidx.test:core-ktx:1.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.2-alpha05")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:${espressoVersion}")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:${espressoVersion}")
    androidTestImplementation("androidx.test:rules:1.3.0")
    androidTestImplementation("com.android.support.test.uiautomator:uiautomator-v18:2.1.3")
    // Note that espresso-idling-resource is used in the code under test.
    implementation("androidx.test.espresso:espresso-idling-resource:${espressoVersion}")
}

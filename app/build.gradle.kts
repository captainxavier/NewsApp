plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.devToolsKsp)
    alias(libs.plugins.daggerHilt)
}

android {
    namespace = "com.xavier.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.xavier.newsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Material
    implementation(libs.androidx.material3)

    //Compose
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    //Navigation Compose
    implementation(libs.navigation.compose)

    //Splash Api
    implementation(libs.androidx.core.splashscreen)

    //android core
    implementation(libs.androidx.core.ktx)

    //Android runtime
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //Dagger Hilt
    implementation(libs.dagger.hilt)
    implementation(libs.core.ktx)
    ksp(libs.dagger.hilt.compiler)

    //Navigation hilt
    implementation(libs.hilt.navigation.compose)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)

    //coil
    implementation(libs.coil.compose)

    //datastore
    implementation(libs.androidx.datastore)

    //Paging
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room)

    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //mockito
    testImplementation(libs.mockito)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.mockK)
    //TRUTH
    testImplementation(libs.truth)
    androidTestImplementation(libs.truth)
}
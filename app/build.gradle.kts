plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("org.jetbrains.kotlin.kapt" )version "1.9.10"
    id ("com.google.devtools.ksp")
}

val corektxVersion = "1.12.0"
val compiler_version = "1.9.10"
val constraintlayoutVersion = "2.1.4"
val activityktxVersion = "1.5.0"
val appcompatVersion = "1.6.1"
val fragmentKtxVersion = "1.5.5"
val recyclerVersion = "1.1.0"
val navigationVersion = "2.5.3"
val mapkitLiteVersion = "4.4.0-lite"
val mapkitFullVersion = "4.4.0-full"
val compose_version = "1.5.3"
val room_version = "2.5.2"


android {
    namespace = "com.example.vtbapiapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vtbapiapp"
        minSdk = 26
        targetSdk = 33
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
    buildFeatures{
        viewBinding = true
        compose = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation ("com.sothree.slidinguppanel:library:3.4.0")
    implementation ("androidx.core:core-ktx:${corektxVersion}")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))

    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.ui:ui-graphics:1.5.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    implementation("androidx.compose.material3:material3:1.1.2")

    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.3")
    implementation("com.yandex.android:maps.mobile:4.4.0-full")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    kapt ("com.android.databinding:compiler:${compiler_version}")
    implementation ("androidx.compose.ui:ui-tooling:1.5.3")
    implementation ("androidx.compose.runtime:runtime:1.5.3")
    implementation ("androidx.compose.runtime:runtime-android:1.5.3")
    implementation ("androidx.compose.compiler:compiler:1.5.3")
//
//    implementation "androidx.room:room-runtime:${rootProject.ext.room_version}"
//    annotationProcessor "androidx.room:room-compiler:${rootProject.ext.room_version}"
//    def room_version = ${rootProject.ext.room_version}

    implementation ("androidx.room:room-runtime:${room_version}")
    annotationProcessor ("androidx.room:room-compiler:${room_version}")

    // To use Kotlin annotation processing tool (kapt)
    ksp ("androidx.room:room-compiler:${room_version}")
    implementation ("androidx.room:room-ktx:${room_version}")
    // To use Kotlin Symbol Processing (KSP)
}
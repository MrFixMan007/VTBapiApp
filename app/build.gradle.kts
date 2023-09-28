plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.vtbapiapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.vtbapiapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Optional -- Mockito framework
    testImplementation ("org.mockito:mockito-core:5.5.0")

    // Room
    implementation ("androidx.room:room-runtime:2.5.2")
    implementation ("androidx.room:room-ktx:2.5.2")

    annotationProcessor ("androidx.room:room-compiler:2.5.2")

    // optional - RxJava2 support for Room
    implementation ("androidx.room:room-rxjava2:2.5.2")

    // optional - RxJava3 support for Room
    implementation ("androidx.room:room-rxjava3:2.5.2")

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation ("androidx.room:room-guava:2.5.2")

    // optional - Test helpers
    testImplementation ("androidx.room:room-testing:2.5.2")

    // optional - Paging 3 Integration
    implementation ("androidx.room:room-paging:2.5.2")
}
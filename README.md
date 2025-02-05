# 📰 Asparagas News App

A modern Android news application built with Kotlin that demonstrates clean architecture principles, MVVM pattern, and the latest Android development practices. The app fetches news from the News API, allows users to browse news, search articles, and bookmark their favorites for offline reading.

## 📸 Screenshots
<p>
      <img src="https://github.com/user-attachments/assets/2c3afa35-d94d-4a16-84b1-011e91565cec" width="24%" title="Dark-Home"/>
      <img src="https://github.com/user-attachments/assets/46b46210-44cd-42e5-b40e-20cb9c0adb34" width="24%" title="Light-Search"/>
      <img src="https://github.com/user-attachments/assets/85910942-b2cd-4c15-9967-0c67eae9baab" width="24%" title="Dark-Detail"/>
      <img src="https://github.com/user-attachments/assets/19397bc8-aa47-47eb-8fdc-6c66e80b5641" width="24%" title="Light-Bookmark"/>
      <img src="https://github.com/user-attachments/assets/3ebdc467-9829-41bc-8c41-eea4abf8697e" width="24%" title="Light-Home"/>
      <img src="https://github.com/user-attachments/assets/b56a2818-79b6-4f85-bb77-9c3c1dd5429d" width="24%" title="Dark-Search"/>
      <img src="https://github.com/user-attachments/assets/c140de9c-c56d-49b8-8ee5-9a9adca0f142" width="24%" title="Light-Detail"/>
      <img src="https://github.com/user-attachments/assets/0ff1840d-1d37-448b-8bd3-68c02bcc3bee" width="24%" title="Dark-Bookmark"/>
</p>

## ✨ Features

### 🏠 Home Screen
- Displays technology news articles in a scrollable list
- Shows article title, description, publication date, and image
- Fetches data from News API's top headlines endpoint

### 🔍 Search Screen
- Search functionality for news articles using News API's everything endpoint
- Results displayed in a responsive list view

### 📑 Article Details
- Full article view with article image, title, description, and content
- Bookmark functionality with local storage
- System insets aware UI

### 🔖 Bookmarks
- View saved articles offline
- Delete individual bookmarks
- Delete all bookmarks
- Persistent storage using Room database

### 🎨 UI/UX
- Material Design 3 components
- Bottom navigation
- Edge-to-edge design with proper system insets handling
- Responsive layouts for all screens

## 🛠️ Tech Stack

### App Architecture
- **Clean Architecture** with domain, data, and presentation layers
- **MVVM Pattern** using ViewModel and LiveData
- **Repository Pattern** for data operations
- **Use Cases** for business logic

### Android Components
- **Jetpack Navigation** - Single activity, multiple fragments
- **Room Database** - Local storage for bookmarks
- **ViewModel** - Lifecycle-aware data holder
- **ViewBinding** - Type-safe view access
- **Hilt** - Dependency injection

### Networking & Data
- **Retrofit** - REST API client
- **OkHttp** - HTTP client with interceptors
- **Gson** - JSON parsing
- **Glide** - Image loading

### UI Components
- **Material Design 3** - UI components and theming
- **RecyclerView** - List handling
- **ConstraintLayout** - Responsive layouts
- **CoordinatorLayout** - Complex UI behaviors

### Build Tools & Plugins
- **Kotlin Android** - Core Android plugin for Kotlin
- **KSP** - Kotlin Symbol Processing for annotation processing
- **Hilt Plugin** - For dependency injection code generation
- **Navigation Safe Args** - Type-safe navigation and argument passing
- **Kotlin Parcelize** - Automatic Parcelable implementation
- **Room Plugin** - Database schema processing
- **Gradle Version Catalogs** - Centralized dependency management
- **BuildConfig** - Custom build configuration fields

## 📂 Project Structure
```plaintext
com.aliozdemir.asparagas
│
├── data
│   ├── local
│   │   ├── dao
│   │   │   └── ArticleDao.kt
│   │   ├── database
│   │   │   ├── AppDatabase.kt
│   │   │   └── TypeConverter.kt
│   │   └── entity
│   │       ├── BookmarkArticleEntity.kt
│   │       └── BookmarkSourceEntity.kt
│   ├── mapper
│   │   ├── DtoMappers.kt
│   │   └── EntityMappers.kt
│   ├── remote
│   │   ├── api
│   │   │   └── NewsApi.kt
│   │   └── dto
│   │       ├── ArticleDto.kt
│   │       ├── NewsDto.kt
│   │       └── SourceDto.kt
│   └── repository
│       └── NewsRepositoryImpl.kt
│
├── di
│   ├── DatabaseModule.kt
│   ├── NetworkModule.kt
│   └── RepositoryModule.kt
│
├── domain
│   ├── model
│   │   ├── Article.kt
│   │   ├── News.kt
│   │   └── Source.kt
│   ├── repository
│   │   └── NewsRepository.kt
│   └── usecase
│       ├── CheckArticleExistsUseCase.kt
│       ├── DeleteAllArticlesUseCase.kt
│       ├── DeleteArticleUseCase.kt
│       ├── GetAllBookmarkedArticlesUseCase.kt
│       ├── GetEverythingUseCase.kt
│       ├── GetTopHeadlinesUseCase.kt
│       └── InsertArticleUseCase.kt
│
├── presentation
│   ├── bookmark
│   │   ├── BookmarkAdapter.kt
│   │   ├── BookmarkFragment.kt
│   │   ├── BookmarkViewHolder.kt
│   │   └── BookmarkViewModel.kt
│   ├── detail
│   │   ├── DetailFragment.kt
│   │   └── DetailViewModel.kt
│   ├── home
│   │   ├── HomeAdapter.kt
│   │   ├── HomeFragment.kt
│   │   ├── HomeViewHolder.kt
│   │   └── HomeViewModel.kt
│   └── search
│       ├── SearchAdapter.kt
│       ├── SearchFragment.kt
│       ├── SearchViewHolder.kt
│       └── SearchViewModel.kt
│
├── util
│   ├── Extensions.kt
│   ├── Resource.kt
│   └── SafeApiCall.kt
│
├── MainActivity.kt
└── MainApp.kt
```

## ⚙️ Build Configuration
`app/buildgradle.kts`: This file contains the build configuration for the Asparagas Android application. It specifies the plugins, dependencies, and other settings required for building the app.
```kotlin
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Hilt
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

    // Navigation - Safe Args - Parcelize
    alias(libs.plugins.navigation.safeargs.kotlin)
    alias(libs.plugins.kotlin.parcelize)

    // Room
    alias(libs.plugins.room)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}
val newsApiKey = localProperties["NEWS_API_KEY"]

android {
    namespace = "com.aliozdemir.asparagas"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aliozdemir.asparagas"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "NEWS_API_KEY", "\"${newsApiKey}\"")
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // OkHttp - Retrofit - Converter Gson - Gson
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)

    // Glide
    implementation(libs.glide)

    // Room
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
}
```

## 🔨 Top-level Build Configuration
`buildgradle.kts`: This file serves as the top-level build configuration for the Asparagas Android project. It applies common settings and plugins for all sub-projects/modules within the application.
```kotlin
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Hilt
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false

    // Navigation - Safe Args
    alias(libs.plugins.navigation.safeargs.kotlin) apply false

    // Room
    alias(libs.plugins.room) apply false
}
```

## 📝 Dependency Versions
`libs.versions.toml`: This file defines the versions of the dependencies used in the Asparagas Android application. It helps manage and update library versions in a centralized manner.
```kotlin
[versions]
agp = "8.8.0"
coreKtx = "1.15.0"
junit = "4.13.2"
junitVersion = "1.2.1"
espressoCore = "3.6.1"
appcompat = "1.7.0"
material = "1.12.0"
activity = "1.10.0"
constraintlayout = "2.2.0"

# Kotlin
kotlin = "2.1.0"

# Hilt
ksp = "2.1.0-1.0.29"
hilt = "2.55"

# Navigation
navigationFragment = "2.8.6"

# OkHttp - Retrofit - Converter Gson - Gson
okhttp = "4.12.0"
retrofit = "2.11.0"
gson = "2.11.0"

# Glide
glide = "4.16.0"

# Room
roomRuntime = "2.6.1"

[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-appcompat = { group = "androidx.appcompat", name = "appcompat", version.ref = "appcompat" }
material = { group = "com.google.android.material", name = "material", version.ref = "material" }
androidx-activity = { group = "androidx.activity", name = "activity", version.ref = "activity" }
androidx-constraintlayout = { group = "androidx.constraintlayout", name = "constraintlayout", version.ref = "constraintlayout" }

# Hilt
hilt-compiler = { group = "com.google.dagger", name = "hilt-compiler", version.ref = "hilt" }
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }

# Navigation
androidx-navigation-fragment = { module = "androidx.navigation:navigation-fragment", version.ref = "navigationFragment" }
androidx-navigation-ui = { module = "androidx.navigation:navigation-ui", version.ref = "navigationFragment" }

# OkHttp - Retrofit - Converter Gson - Gson
okhttp = { module = "com.squareup.okhttp3:okhttp", version.ref = "okhttp" }
retrofit = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }
converter-gson = { module = "com.squareup.retrofit2:converter-gson", version.ref = "retrofit" }
gson = { module = "com.google.code.gson:gson", version.ref = "gson" }

# Glide
glide = { module = "com.github.bumptech.glide:glide", version.ref = "glide" }

# Room
androidx-room-compiler = { module = "androidx.room:room-compiler", version.ref = "roomRuntime" }
androidx-room-runtime = { module = "androidx.room:room-runtime", version.ref = "roomRuntime" }
androidx-room-ktx = { module = "androidx.room:room-ktx", version.ref = "roomRuntime" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }

# Hilt
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
hilt = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }

# Navigation - Safe Args - Parcelize
navigation-safeargs-kotlin = { id = "androidx.navigation.safeargs.kotlin", version.ref = "navigationFragment" }
kotlin-parcelize = { id = "kotlin-parcelize" }

# Room
room = { id = "androidx.room", version.ref = "roomRuntime" }
```

## 📱 Tested Versions
The application has been tested on the following Android API levels:
- API 29 ("Q"; Android 10.0)
- API 30 ("R"; Android 11.0)
- API 31 ("S"; Android 12.0)
- API 32 ("Sv2"; Android 12L)
- API 33 ("Tiramisu"; Android 13.0)
- API 34 ("UpsideDownCake"; Android 14.0)
- API 35 ("VanillaIceCream"; Android 15.0)

import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.util.Date
import java.util.Properties

val versionMajor = 0
val versionMinor = 1
val versionPatch = 0

fun getBuildNumber(): Int {
  val df = SimpleDateFormat("yyyyMMdd")
  val date = LocalDateTime.now()
  val seconds =
      (Duration.between(date.withSecond(0).withMinute(0).withHour(0), date).seconds / 86400) * 99.0
  val twoDigitSuffix = seconds.toInt()

  return Integer.parseInt(df.format(Date()) + String.format("%02d", twoDigitSuffix))
}

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.kotlinAndroid)
  id("io.sentry.android.gradle") version "3.13.0"
}

sentry {
  includeSourceContext.set(true)

  org.set("davwheat")
  projectName.set("shannon-modem-tweaks")
  authToken.set(System.getenv("SENTRY_AUTH_TOKEN"))
}

android {
  namespace = "dev.davwheat.shannonmodemtweaks"
  compileSdk = 34
  buildToolsVersion = "34.0.0"

  defaultConfig {
    applicationId = "dev.davwheat.shannonmodemtweaks"
    testApplicationId = "com.trainsplit.trainsplit.test"
    minSdk = 31
    targetSdk = 34
    versionCode = getBuildNumber()
    versionName = "${versionMajor}.${versionMinor}.${versionPatch}"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables { useSupportLibrary = true }
  }

  signingConfigs {
    create("release") {
      val properties = Properties()
      properties.load(FileInputStream(project.rootProject.file("local.properties")))

      storeFile = file(properties.getProperty("signing.storeFilePath"))
      storePassword = properties.getProperty("signing.storePassword")
      keyAlias = properties.getProperty("signing.keyAlias")
      keyPassword = properties.getProperty("signing.keyPassword")
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("release")
      isDebuggable = false

      manifestPlaceholders["sentryEnvironment"] = "production"
    }

    debug {
      signingConfig = signingConfigs.getByName("debug")
      isDebuggable = true

      manifestPlaceholders["sentryEnvironment"] = "development"
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions { jvmTarget = "1.8" }
  buildFeatures { compose = true }
  composeOptions { kotlinCompilerExtensionVersion = "1.5.1" }
  packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}

tasks.withType(Test::class) {
  useJUnitPlatform()

  testLogging {
    events("passed", "skipped", "failed")
  }
}

dependencies {
  implementation(libs.core.ktx)
  implementation(libs.lifecycle.runtime.ktx)
  implementation(libs.activity.compose)
  implementation(platform(libs.compose.bom))
  implementation(libs.ui)
  implementation(libs.ui.graphics)
  implementation(libs.ui.tooling.preview)
  implementation(libs.material3)
  implementation(libs.timber)
  implementation(libs.androidx.material.icons.extended)
  implementation(libs.androidx.navigation.compose)
  testImplementation(libs.junit)
  testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.espresso.core)
  androidTestImplementation(platform(libs.compose.bom))
  androidTestImplementation(libs.ui.test.junit4)
  debugImplementation(libs.ui.tooling)
  debugImplementation(libs.ui.test.manifest)
}

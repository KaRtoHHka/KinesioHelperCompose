import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.0"
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "kartohhka.desktop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
//    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven ("https://jitpack.io" )
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("com.darkrockstudios:mpfilepicker:3.1.0")
    implementation("io.github.dokar3:sonner:0.3.4")
    implementation("org.apache.poi:poi:5.2.5")
    implementation("org.apache.poi:poi-ooxml:5.2.5")

    implementation("org.mongodb:bson:4.11.1")
    implementation("org.mongodb:mongodb-driver-core:4.11.1")
    implementation("org.mongodb:mongodb-driver-sync:4.11.1")
    implementation("org.litote.kmongo:kmongo:4.11.0")
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.0.0")
    implementation("org.mongodb:bson-kotlinx:5.0.0")
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            windows.shortcut = true
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "kinesio_helper_compose"
            packageVersion = "1.0.0"
        }
    }
}


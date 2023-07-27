plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version Versions.composeDesktop
    application
}

group = "me.joreilly"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.jxmapviewer:jxmapviewer2:2.6")
    implementation(project(":common"))
}

application {
    mainClass.set("MainKt")
}

compose {
    kotlinCompilerPlugin.set(Versions.jbComposeCompiler)
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.0")
}

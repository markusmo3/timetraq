import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
}

group = "com.github.markusmo3.timetraq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("net.java.dev.jna:jna:5.2.+")
    compile("net.java.dev.jna:jna-platform:5.2.+")
    compile("no.tornado:tornadofx:1.7.+")
    compile("org.jetbrains.exposed:exposed:0.12.+")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
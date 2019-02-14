import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("net.java.dev.jna:jna:5.2.+")
    compile("net.java.dev.jna:jna-platform:5.2.+")
    compile("org.jetbrains.exposed:exposed:0.12.+")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
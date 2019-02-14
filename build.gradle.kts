import java.net.URI

plugins {
    base
    kotlin("jvm") version "1.3.20" apply false
}

allprojects {

    group = "com.github.markusmo3.timetraq"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven {
            url = URI.create("https://dl.bintray.com/kotlin/exposed/")
        }
    }
}

dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}
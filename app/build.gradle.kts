import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    application
    jacoco
    checkstyle
    id("org.sonarqube") version "6.2.0.5505"
}


application {
    mainClass.set("hexlet.code.App")
}

sonar {
    properties {
        property("sonar.projectKey", "ZyrT12_java-project-71")
        property("sonar.organization", "zyrt12")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.token", System.getenv("SONAR_TOKEN"))
    }
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
}

tasks.test {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.10"
}

/*jacocoTestReport {

    reports {

        xml.required.set(true)
    }
    classDirectories.setFrom(
        fileTree("build/classes/java/main") {
        }
    )
    sourceDirectories.setFrom(files("src/main/java"))
    executionData.setFrom(fileTree(buildDir).include("jacoco/test.exec"))
}
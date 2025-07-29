import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    application
    jacoco
    checkstyle
}


application {
    mainClass.set("hexlet.code.App")
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
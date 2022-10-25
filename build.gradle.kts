plugins {
    id("java")
    id("org.checkerframework") version "0.6.18"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

dependencies {
    compileOnly("org.jetbrains:annotations:17.0.0")
    compileOnly("org.checkerframework:checker-qual:3.26.0")
    checkerFramework("org.checkerframework:checker:3.26.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

configure<org.checkerframework.gradle.plugin.CheckerFrameworkExtension> {
    excludeTests = true
    extraJavacArgs = listOf(
        "-AprintAllQualifiers",
        "-AstubNoWarnIfNotFound=true",
        "-Astubs=${project.rootDir}/checkerframework"
    )
    checkers = listOf(
        "org.checkerframework.checker.nullness.NullnessChecker",
    )
}

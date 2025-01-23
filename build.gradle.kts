plugins {
    kotlin("jvm") version "2.0.0"

}

group = "com.canorhan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.choco-solver:choco-solver:4.10.17")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
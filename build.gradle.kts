import org.gradle.jvm.tasks.Jar


plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("kapt") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
}

group = "nl.lengrand"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("info.picocli:picocli:4.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")

    implementation("com.github.kittinunf.fuel:fuel:2.3.0")
    implementation("com.github.kittinunf.fuel:fuel-kotlinx-serialization:2.3.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")

    kapt("info.picocli:picocli-codegen:4.5.0")
}

val fatJar = task("customFatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Implementation-Title"] = "SWACli"
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "nl.lengrand.swacli.SWACliBasicKt"
    }
    from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks.jar.get() as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

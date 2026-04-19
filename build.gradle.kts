plugins {
    java
    id("com.gradleup.shadow") version "9.3.2"
}

group = "io.github.thebusybiscuit"
version = "1.0.0"
description = "ExoticGarden is a Slimefun addon adding exotic plants and food."

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
    compileOnly(fileTree("${rootDir}/../Slimefun5/build/libs") { include("*.jar") })
    implementation("org.bstats:bstats-bukkit:3.0.2")

    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.15.2")
    testImplementation("org.slf4j:slf4j-simple:2.0.16")
    testImplementation("org.mockbukkit.mockbukkit:mockbukkit-v1.21:4.107.0") {
        exclude(group = "org.jetbrains", module = "annotations")
    }
}

configurations.testImplementation {
    extendsFrom(configurations.compileOnly.get())
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-Xlint:deprecation")
    }
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to project.version)
        }
        // NOTE: schematics are binary files — only expand plugin.yml, NOT schematics
    }
    jar {
        enabled = false
    }
    shadowJar {
        archiveFileName.set("ExoticGarden v${project.version}.jar")
        relocate("org.bstats", "io.github.thebusybiscuit.exoticgarden.bstats")
        exclude("META-INF/**")
    }
    build {
        dependsOn(shadowJar)
    }
    test {
        useJUnitPlatform()
    }
}

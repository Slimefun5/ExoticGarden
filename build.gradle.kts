plugins {
    java
    id("com.gradleup.shadow")
    id("io.github.intisy.github-gradle") version "1.8.3"
}

group = "io.github.thebusybiscuit"
<<<<<<< HEAD
<<<<<<< HEAD
version = "v1.0.0-UNOFFICIAL-MC26.1.2"
<<<<<<< HEAD
description = "Adds new Plants, Berries, Trees, Fruits, Vegetables and Food to Slimefun"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    maven("https://jitpack.io")
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation("com.github.Slimefun5:SlimefunMetrics:master-SNAPSHOT")
    "githubCompileOnly"("Slimefun5:Slimefun5:v5.1.1")
    compileOnly("io.papermc.paper:paper-api:${property("paperApiVersion")}")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    build {
        dependsOn(shadowJar)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.thebusybiscuit"
            artifactId = "exoticgarden"
            version = "7f9a5f6"
            artifact(tasks.shadowJar)
        }
    }
}

group = "io.github.thebusybiscuit"
version = "1.0.0"
=======
>>>>>>> origin/experimental
=======
version = "1.7.3"
>>>>>>> origin/experimental
=======
>>>>>>> origin/experimental
description = "ExoticGarden is a Slimefun addon adding exotic plants and food."

fun latestGitTagVersion(): String? = try {
    val out = providers.exec { workingDir = rootDir; commandLine("git","describe","--tags","--abbrev=0"); isIgnoreExitValue = true }
    if (out.result.get().exitValue == 0) out.standardOutput.asText.get().trim().removePrefix("gh-").removePrefix("v").takeIf { it.isNotBlank() } else null
} catch (e: Exception) { null }

version = (project.findProperty("artifact_version") as String?)?.removePrefix("v")?.takeIf { it.isNotBlank() } ?: latestGitTagVersion() ?: "1.7.3"
val versionSuffix: String = when {
    !(project.findProperty("artifact_version") as String?).isNullOrBlank() -> ""
    System.getenv("GITHUB_ACTIONS") == "true" -> "-EXPERIMENTAL"
    else -> "-UNOFFICIAL"
}
val displayVersion = "${project.version}$versionSuffix"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

repositories {
    maven("https://jitpack.io")
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.codemc.io/repository/maven-public/")
}

dependencies {
<<<<<<< HEAD
    implementation("com.github.Slimefun5:SlimefunMetrics:master-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:${property("paperApiVersion")}")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
    "githubCompileOnly"("Slimefun5:Slimefun5:v5.1.1")
    
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
=======
    githubCompileOnly("Slimefun5:Slimefun5:gh-v5.2.3.2")
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
>>>>>>> origin/experimental
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to displayVersion)
        }
    }
    jar {
        enabled = false
    }
    shadowJar {
        archiveFileName.set("ExoticGarden-$displayVersion.jar")
        exclude("META-INF/**")
    }
    build {
        dependsOn(shadowJar)
    }
    compileTestJava {
        enabled = false
    }
    test {
        enabled = false
    }
}

github {
    accessToken = System.getenv("GITHUB_TOKEN") ?: ""
    publish {
        tag = System.getenv("GITHUB_REF_NAME")
    }
}

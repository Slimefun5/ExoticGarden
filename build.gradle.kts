plugins {
    java
    id("com.gradleup.shadow")
    id("io.github.intisy.github-gradle") version "1.8.3"
}

group = "io.github.thebusybiscuit"
description = "ExoticGarden is a Slimefun addon adding exotic plants and food."

// Shared Slimefun-addon build conventions (Java 8, spigot-api baseline, core dep, publish, shadow, version).
apply(from = "https://raw.githubusercontent.com/Slimefun5/workflows/stable/slimefun-addon.gradle")

// ExoticGarden ships no tests.
tasks {
    compileTestJava { enabled = false }
    test { enabled = false }
}

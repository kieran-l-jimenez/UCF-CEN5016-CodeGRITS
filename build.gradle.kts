plugins {
    id("java")
//    id("org.jetbrains.kotlin.jvm") version "1.8.21"
    id("org.jetbrains.intellij") version "1.13.3"
}

group = "com.nd"
version = "0.2.0"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.opencsv/opencsv
    implementation("com.opencsv:opencsv:5.7.1")
    // implementation("org.bytedeco:javacv-platform:1.5.9")
    implementation("org.bytedeco:javacv:1.5.9")
    implementation("org.bytedeco:ffmpeg:6.0-1.5.9")
    implementation("org.bytedeco:ffmpeg-platform:6.0-1.5.9")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.1.4")

    type.set("IC")
    plugins.set(listOf("com.intellij.java"))

//    type.set("PC")
//    plugins.set(listOf("PythonCore"))

//    type.set("CL")

//    type.set("PS")

//    version.set("2023.1.4")
//    type.set("IC")
//    plugins.set(listOf("android"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
//    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//        kotlinOptions.jvmTarget = "17"
//    }

    patchPluginXml {
        sinceBuild.set("222")
        untilBuild.set("232.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    val createOpenApiSourceJar by registering(Jar::class) {
        from(sourceSets.main.get().java) {
            include("**/api/**/*.java")
        }
        destinationDirectory.set(layout.buildDirectory.dir("libs"))
        archiveClassifier.set("src")
    }

    buildPlugin {
        dependsOn(createOpenApiSourceJar)
        from(createOpenApiSourceJar) { into("lib/src") }
    }
}

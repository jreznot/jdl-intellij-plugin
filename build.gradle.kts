plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.8.0"
}

group = "org.strangeway.jdl"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

java.sourceSets["main"].java {
  srcDir("src/main/gen")
}

intellij {
  version.set("2021.3.3")
  type.set("IC")

  plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "11"
    targetCompatibility = "11"
  }

  patchPluginXml {
    sinceBuild.set("213")
    untilBuild.set("223.*")
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}

tasks {
  val test by getting(Test::class) {
    isScanForTestClasses = false
    // Only run tests from classes that end with "Test"
    include("**/*Test.class")
  }
}
plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.13.3"
}

group = "org.strangeway.jdl"
version = "2.3.3"

repositories {
  mavenCentral()
}

java.sourceSets["main"].java {
  srcDir("src/main/gen")
}

intellij {
  version.set("2023.1")
  type.set("IU")

  plugins.set(listOf("com.intellij.java", "uml"))
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
  }

  patchPluginXml {
    sinceBuild.set("231")
    untilBuild.set("233.*")

    changeNotes.set("""
      Icons changed to be compatible with new UI
    """.trimIndent())
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }

  withType(Test::class) {
    isScanForTestClasses = false
    // Only run tests from classes that end with "Test"
    include("**/*Test.class")
  }
}

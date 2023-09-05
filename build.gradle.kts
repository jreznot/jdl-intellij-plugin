plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.15.0"
}

group = "org.strangeway.jdl"
version = "2.4.2"

repositories {
  mavenCentral()
}

java.sourceSets["main"].java {
  srcDir("src/main/gen")
}

intellij {
  version.set("2023.2")
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
    sinceBuild.set("232")
    untilBuild.set("241.*")

    changeNotes.set("""
      Enabled JDL highlighting during indexing. 
      Support relationship options syntax.
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

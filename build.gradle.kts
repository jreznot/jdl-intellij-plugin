plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.9.0"
}

group = "org.strangeway.jdl"
version = "2.2.0"

repositories {
  mavenCentral()
}

java.sourceSets["main"].java {
  srcDir("src/main/gen")
}

intellij {
  version.set("2022.2.4")
  type.set("IU")

  plugins.set(listOf("com.intellij.java", "uml"))
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "11"
    targetCompatibility = "11"
  }

  patchPluginXml {
    sinceBuild.set("222")
    untilBuild.set("232.*")

    changeNotes.set("""
      <ul>
        <li>UML preview in editor</li>
      </ul>
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
}

tasks {
  @Suppress("UNUSED_VARIABLE")
  val test by getting(Test::class) {
    isScanForTestClasses = false
    // Only run tests from classes that end with "Test"
    include("**/*Test.class")
  }
}

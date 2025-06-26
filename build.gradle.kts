repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}
// Root build.gradle.kts


tasks.register("listrepos") {
    doLast {
        println("Repositories:")
        project.repositories.map{it as MavenArtifactRepository}
            .forEach{
                println("Name: ${it.name}; url: ${it.url}")
            }
    }
}
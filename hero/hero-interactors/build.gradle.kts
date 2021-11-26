plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.heroDataSource))
    "implementation"(project(Modules.heroDomain))



}
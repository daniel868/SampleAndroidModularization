plugins {
    id("java-library")
    id("kotlin")
    kotlin(KotlinPlugins.serialization) version "1.5.30"
    id(SQLDelight.plugin)
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}


dependencies {

    "implementation"(project(Modules.heroDomain))

    "implementation"(Ktor.core)
    "implementation"(Ktor.clientSerialization)
    "implementation"(Ktor.android)

    "implementation"(SQLDelight.runtime)

}

sqldelight{
    database("HeroDatabase"){
        packageName="com.example.hero_datasource.cache"
        sourceFolders = listOf("sqldelight")
    }
}



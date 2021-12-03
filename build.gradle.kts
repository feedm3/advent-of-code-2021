plugins {
    java
    idea
}

group = "me.dietenberger"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:31.0.1-jre")
    implementation("org.apache.commons:commons-lang3:3.12.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
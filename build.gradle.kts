

plugins {
    id("java")
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}



group = "com.candaceBot"
version = "1.0-SNAPSHOT"

val jdaVersion = "5.0.0-beta.17"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("ch.qos.logback:logback-classic:1.2.8")
    implementation("org.json:json:20210307")
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_9
    targetCompatibility = JavaVersion.VERSION_1_9
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true

    // Set this to the version of java you want to use,
    // the minimum required for JDA is 1.8
    sourceCompatibility = "1.8"
}

tasks.test {
    useJUnitPlatform()
}
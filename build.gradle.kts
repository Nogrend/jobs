plugins {
    java
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "nogrend"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.postgresql:postgresql:42.7.6")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("net.javacrumbs.shedlock:shedlock-spring:6.6.1")
    implementation("net.javacrumbs.shedlock:shedlock-provider-jdbc-template:6.6.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

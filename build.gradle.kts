import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("com.google.cloud.tools.jib") version "3.2.1"
	id("org.asciidoctor.jvm.convert") version "3.1.0"
}

group = "bcg.dv"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
// Jib
jib {
	from {
		image = "openjdk:11-jre-slim"
	}
	to {
		image = "271412685121.dkr.ecr.eu-central-1.amazonaws.com/backend"
		tags = setOf("latest", "${project.name}-${project.version}".toString())
	}

	container {
		mainClass = "com.bcgdv.fr.FamousRoastersApplication"
		ports = listOf("8080")
		volumes = listOf("/tmp")
		user = "nobody:nogroup"
		creationTime = "USE_CURRENT_TIMESTAMP"
	}
}
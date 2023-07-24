val NEXUS_CENTRAL_URL : String by project
val NEXUS_SNAPSHOT_URL : String by project
val NEXUS_RELEASE_URL : String by project
val NEXUS_USERNAME : String by project
val NEXUS_PASSWORD : String by project
val PROJECT_GROUP : String by project
val PROJECT_VERSION : String by project
val PROJECT_ARTIFACT_ID : String by project

plugins {
	`maven-publish`
	java
	id ("org.springframework.boot") version "2.7.11"
	id ("io.spring.dependency-management") version "1.1.0"
}


group= PROJECT_GROUP
version= PROJECT_VERSION

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}


publishing {
	publications {
		create<MavenPublication>("mavenBootJar") {
			groupId = PROJECT_GROUP
			artifactId = PROJECT_ARTIFACT_ID
			version = PROJECT_VERSION
			from(components["java"])
			// task 스냅샷을 라이브러리로 설정함
			artifact(tasks.named("bootJar").get())
		}
	}
	repositories {
		maven {
			url = uri(if (project.hasProperty("RELEASE")) NEXUS_RELEASE_URL else NEXUS_SNAPSHOT_URL)
			isAllowInsecureProtocol = true
			credentials {
				username = NEXUS_USERNAME
				password = NEXUS_PASSWORD
			}
		}
	}
}

repositories {
	maven {
		url = uri(NEXUS_CENTRAL_URL)
		isAllowInsecureProtocol = true
		credentials {
			username = NEXUS_USERNAME
			password = NEXUS_PASSWORD
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("commons-codec:commons-codec:1.15")

	runtimeOnly("com.mysql:mysql-connector-j")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testCompileOnly("org.projectlombok:lombok:1.18.12")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.12")
}

// 일반 아카이브 설정 지움
tasks.named<Jar>("jar") {
	enabled = false
}
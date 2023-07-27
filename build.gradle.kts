val NEXUS_CENTRAL_URL : String by project
val NEXUS_SNAPSHOT_URL : String by project
val NEXUS_PUBLIC_URL : String by project
val NEXUS_USERNAME : String by project
val NEXUS_PASSWORD : String by project
val PROJECT_GROUP : String by project
val PROJECT_VERSION : String by project
val PROJECT_ARTIFACT_ID : String by project

plugins {
	java
	`maven-publish`
	id ("org.springframework.boot") version "2.7.11"
	id ("io.spring.dependency-management") version "1.1.0"
}


group= PROJECT_GROUP
version= PROJECT_VERSION

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

repositories {
	maven {
		url = uri(NEXUS_PUBLIC_URL)
		isAllowInsecureProtocol = true
	}

	maven {
		url = uri(NEXUS_CENTRAL_URL)
		isAllowInsecureProtocol = true
		credentials {
			username = NEXUS_USERNAME
			password = NEXUS_PASSWORD
		}
	}
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = PROJECT_GROUP
			artifactId = PROJECT_ARTIFACT_ID
			version = PROJECT_VERSION
			from(components["java"])
		}
	}
	repositories {
		maven {
			url = uri(NEXUS_SNAPSHOT_URL)
			isAllowInsecureProtocol = true
			credentials {
				username = NEXUS_USERNAME
				password = NEXUS_PASSWORD
			}
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-web-services")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("commons-codec:commons-codec:1.15")
	runtimeOnly("com.mysql:mysql-connector-j")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
}


tasks.named<Jar>("bootJar") {
	enabled = false
}

tasks.named<Jar>("jar") {
	enabled = true
}
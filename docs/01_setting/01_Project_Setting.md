## Spring Boot 프로젝트 생성  
**커밋:** [c001](https://github.com/farrar142-examples/p-14646-1/commit/c001)  
**트리:** [c001](https://github.com/farrar142-examples/p-14646-1/tree/c001)  

---

## 작업

### 작업 1: Spring Boot 프로젝트 초기 설정

- Spring Boot **4.0.0** 기반의 백엔드 프로젝트 생성
- **Java 25**를 언어 버전으로 설정
- 빌드 도구로 **Gradle** 사용
- **Kotlin DSL (`build.gradle.kts`)** 적용

---

### 작업 2: 주요 의존성 추가 (`build.gradle.kts`)

```kotlin
plugins {
    java
    id("org.springframework.boot") version "4.0.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")

    testImplementation("org.springframework.boot:spring-boot-starter-data-elasticsearch-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
````

#### 주요 스타터 설명

* **spring-boot-starter-web**
  Spring MVC 기반 웹 애플리케이션을 위한 스타터

* **spring-boot-starter-data-elasticsearch**
  Spring Data Elasticsearch 스타터

  * JPA가 RDB를 추상화하듯, Elasticsearch를 추상화
  * Repository 패턴을 통해 Elasticsearch에 접근 가능

💡 **JPA와의 차이점**

* JPA:
  `spring-boot-starter-data-jpa` + DB 드라이버(H2, MySQL 등) 필요
* Elasticsearch:
  단일 스타터에 **클라이언트까지 포함**

---

### 작업 3: Git 설정

* **`.gitignore`**

  * Gradle 빌드 파일
  * IDE 설정 파일
  * 빌드 산출물 제외

* **`.gitattributes`**

  * 라인 엔딩 설정
  * `gradlew`: **LF**
  * `*.bat`: **CRLF**
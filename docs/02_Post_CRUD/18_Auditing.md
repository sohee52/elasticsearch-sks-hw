# Auditing 설정

- 커밋 [c018](https://github.com/farrar142-examples/p-14646-1/commit/c018)
- 트리 [c018](https://github.com/farrar142-examples/p-14646-1/tree/c018)

---

## 작업

### 작업 1: AuditingConfig 클래스 생성

```java
@Configuration
public class AuditingConfig {

    @Bean
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }
}
````

* `DateTimeProvider` Bean 등록
* `OffsetDateTime` 타입 지원을 위한 설정

---

### 작업 2: BackApplication에 Auditing 활성화

* `@EnableElasticsearchAuditing` 어노테이션 추가
* `@CreatedDate`, `@LastModifiedDate` 어노테이션 자동 처리
* JPA의 `@EnableJpaAuditing`과 동일한 역할

---

### 작업 3: Post Document에 Persistable 구현

* `Persistable<String>` 인터페이스 구현
* `isNew()` 메서드:

  * ID가 `null`이거나
  * 날짜 필드가 모두 `null`이면 새 엔티티로 판단
* Elasticsearch는 ID 유무로 insert/update를 구분하지 않으므로 필요

⚠️ **Persistable 인터페이스가 필요한 이유**

* JPA: 영속성 컨텍스트가 엔티티의 상태
  (transient, managed, detached)를 관리
* Elasticsearch: 영속성 컨텍스트가 없으므로
  `isNew()` 메서드로 새 문서인지 판단
* `@CreatedDate`가 올바르게 설정되려면
  `isNew()`가 적절히 구현되어야 함
* ID가 존재하는 새 문서일 수 있으므로
  (사용자 지정 ID) 날짜 필드도 함께 확인

---

### 작업 4: PostService에서 수동 lastModifiedAt 설정 제거

* Auditing이 자동으로 처리하므로
  `setLastModifiedAt()` 호출 제거
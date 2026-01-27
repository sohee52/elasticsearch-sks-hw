# BaseDocument 추상 클래스 추출 (리팩토링)

- **커밋:** [c019](https://github.com/farrar142-examples/p-14646-1/commit/c019)  
- **트리:** [c019](https://github.com/farrar142-examples/p-14646-1/tree/c019)

---

## 작업

### 작업 1: BaseDocument 추상 클래스 생성

```java
@Getter
@ToString
public class BaseDocument<ID> implements Persistable<ID> {

    @Id
    private ID id;

    @Field(
            type = FieldType.Date,
            format = DateFormat.date_time
    )
    @CreatedDate
    private OffsetDateTime createdAt;

    @Field(
            type = FieldType.Date,
            format = DateFormat.date_time
    )
    @LastModifiedDate
    private OffsetDateTime lastModifiedAt;

    @Override
    public boolean isNew() {
        return id == null || (createdAt == null && lastModifiedAt == null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseDocument<?> that = (BaseDocument<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
````

---

## 💡 JPA의 BaseEntity와 비교

```java
// JPA BaseEntity - Persistable 필요 없음
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
}
```

### 주요 차이점

| 항목          | JPA                 | Elasticsearch             |
| ----------- | ------------------- | ------------------------- |
| 상속 어노테이션    | `@MappedSuperclass` | 없음 (일반 상속)                |
| ID 생성       | `@GeneratedValue`   | 자동 UUID (설정 불필요)          |
| Persistable | 보통 불필요              | 필수 (`isNew()` 구현)         |
| 필드 매핑       | 자동                  | `@Field(type, format)` 필요 |

---

## 모든 Document의 공통 필드 추출

* **id**: 문서 식별자
* **createdAt**: 생성일시 (`@CreatedDate`)
* **lastModifiedAt**: 수정일시 (`@LastModifiedDate`)

### 추가 구현 사항

* `Persistable<ID>` 인터페이스 구현
* `equals()`, `hashCode()`를 ID 기반으로 오버라이드

---

## 작업 2: Post Document 리팩토링

* `BaseDocument<String>` 상속으로 변경
* `@EqualsAndHashCode(callSuper = true)`
  → 부모 클래스 필드 포함
* `@ToString(callSuper = true)`
  → 부모 클래스 필드 포함
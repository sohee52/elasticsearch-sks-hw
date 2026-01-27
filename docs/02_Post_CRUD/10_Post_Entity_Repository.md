# Post 엔티티 및 Repository 생성  
**커밋:** [c010](https://github.com/farrar142-examples/p-14646-1/commit/c010)  
**트리:** [c010](https://github.com/farrar142-examples/p-14646-1/tree/c010)  

---

## 작업 1: Post Document 클래스 생성

```java
@Document(indexName = "posts")
public class Post {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword)
    private String author;

    @Field(
        type = FieldType.Date,
        format = DateFormat.date_time
    )
    private OffsetDateTime createdAt;

    @Field(
        type = FieldType.Date,
        format = DateFormat.date_time
    )
    private OffsetDateTime lastModifiedAt;
}
```

### 어노테이션 설명

* `@Document(indexName = "posts")`
  → Elasticsearch에 `posts` 인덱스 생성
  → JPA의 `@Entity + @Table(name = "posts")`에 해당

### 필드 정의 및 타입 매핑

* **id**

  * 문서 식별자 (`@Id`)
  * ⚠️ Elasticsearch에서는 ID가 보통 `String` 타입
  * 자동 생성 시 UUID 형태의 문자열이 할당됨

* **title**

  * 제목
  * `FieldType.Text` → 전문 검색 가능

* **content**

  * 내용
  * `FieldType.Text` → 전문 검색 가능

* **author**

  * 작성자
  * `FieldType.Keyword` → 정확한 일치 검색용

* **createdAt**

  * 생성 일시
  * `FieldType.Date`

* **lastModifiedAt**

  * 수정 일시
  * `FieldType.Date`

---

## 💡 FieldType.Text vs FieldType.Keyword

* **Text**

  * 형태소 분석됨
  * 부분 검색 가능
  * 예: `"Spring Boot"` 검색 시 `"Spring"`, `"Boot"` 각각 매칭

* **Keyword**

  * 분석되지 않음
  * 정확히 일치해야 검색됨
  * 예: 이메일, 상태값, ID 참조용

---

## RDB vs Elasticsearch 검색 방식 비교

| 상황        | RDB                     | Elasticsearch         |
| --------- | ----------------------- | --------------------- |
| 정확한 일치 검색 | `WHERE author = 'kim'`  | `FieldType.Keyword`   |
| 부분 문자열 검색 | `LIKE '%keyword%'` (느림) | `FieldType.Text` (빠름) |
| 전문 검색     | `FULLTEXT` 인덱스 (제한적)    | `FieldType.Text` (강력) |

### 핵심 차이

* **RDB**

  * 데이터 저장 후에도 검색 방식을 쿼리 시점에 선택 가능
  * (`=`, `LIKE`, `FULLTEXT` 등)

* **Elasticsearch**

  * 검색 방식이 **저장 시점에 필드 타입으로 고정**
  * 필드 타입 변경 시 **인덱스 재생성(마이그레이션)** 필요

---

## 둘 다 필요하면 Multi-field 사용

```java
@MultiField(
    mainField = @Field(type = FieldType.Text),
    otherFields = @InnerField(
        suffix = "keyword",
        type = FieldType.Keyword
    )
)
private String title;
```

* `title` → 전문 검색
* `title.keyword` → 정확한 매칭 검색

---

## 작업 2: PostRepository 인터페이스 생성

```java
public interface PostRepository
        extends ElasticsearchRepository<Post, String> {
}
```

### 설명

* `ElasticsearchRepository<Post, String>` 상속

  * 첫 번째 제네릭: 엔티티 타입
  * 두 번째 제네릭: ID 타입 (`String`)
* JPA의 `JpaRepository<Post, Long>`에 해당

---

## 💡 기본 제공 메서드 비교

| 메서드              | JpaRepository | ElasticsearchRepository |
| ---------------- | ------------- | ----------------------- |
| `save(entity)`   | ✅             | ✅                       |
| `findById(id)`   | ✅             | ✅                       |
| `findAll()`      | ✅ (List 반환)   | ✅ (Iterable 반환)         |
| `delete(entity)` | ✅             | ✅                       |
| `count()`        | ✅             | ✅                       |
| `existsById(id)` | ✅             | ✅                       |
| `flush()`        | ✅             | ❌                       |
| `saveAndFlush()` | ✅             | ❌                       |

---

## 작업 3: BackApplication 설정

* `@EnableElasticsearchRepositories`

  * Elasticsearch Repository 자동 스캔 활성화

---

## 작업 4: PostService에 Repository 연결

* `PostRepository` 의존성 주입
* `count()` 메서드를
  → `postRepository.count()` 호출로 변경
# Comment 엔티티 및 기본 구조 생성

**커밋:** [c020](https://github.com/farrar142-examples/p-14646-1/commit/c020)  
**트리:** [c020](https://github.com/farrar142-examples/p-14646-1/tree/c020)

---

## 작업

### 작업 1: Comment Document 클래스 생성

```java
@Document(indexName = "comments")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseDocument<String> {

    @Field(type = FieldType.Keyword)
    private String postId;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword)
    private String author;

    public Comment(String postId, String content, String author) {
        this.postId = postId;
        this.content = content;
        this.author = author;
    }
}
````

* `BaseDocument<String>` 상속으로 공통 필드 재사용
* `@Document(indexName = "comments")`
  → Elasticsearch에 `comments` 인덱스 생성

#### 필드 정의

* **postId**: 연관 Post ID (`FieldType.Keyword`)
  → 정확한 일치 검색용
* **content**: 댓글 내용 (`FieldType.Text`)
  → 전문 검색 가능
* **author**: 작성자 (`FieldType.Keyword`)

---

## ⚠️ JPA와의 차이점 - 관계 매핑 없음

### JPA에서의 관계 매핑 예시

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "post_id")
private Post post;
```

### Elasticsearch 방식

Elasticsearch에는 JOIN 개념이 없으므로 **ID만 저장**합니다.

```java
@Field(type = FieldType.Keyword)
private String postId; // 단순 ID 참조
```

* Post 정보가 필요하면 애플리케이션에서 별도로 조회
* 또는 비정규화(denormalization)하여 Post 데이터를 Comment에 복사 가능

---

## 작업 2: CommentRepository 인터페이스 생성

```java
public interface CommentRepository
        extends ElasticsearchRepository<Comment, String> {
}
```

* `ElasticsearchRepository<Comment, String>` 상속

---

## 작업 3: CommentService 클래스 생성

```java
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public long count() {
        return commentRepository.count();
    }
}
```

* `@Service` 어노테이션으로 서비스 컴포넌트 지정
* `count()` 메서드 구현

---

## 작업 4: BaseInitData에 CommentService 주입

* `work6` 추가
* Comment 개수 로깅
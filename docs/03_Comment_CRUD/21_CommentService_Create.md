# Comment 생성 기능 구현 (Create)

**커밋:** [c021](https://github.com/farrar142-examples/p-14646-1/commit/c021)  
**트리:** [c021](https://github.com/farrar142-examples/p-14646-1/tree/c021)

---

## 작업

### 작업 1: CommentService.create() 메서드 구현

```java
public Comment create(Post post, String content, String author) {
    Comment comment = new Comment(post.getId(), content, author);
    return commentRepository.save(comment);
}
````

* `Post` 객체를 받아 `post.getId()`로 `postId` 설정
* Post와 Comment의 **느슨한 연결**
  → Elasticsearch는 JOIN을 지원하지 않음

---

## ⚠️ FK 제약조건 없음

### JPA의 경우

* `@ManyToOne` 관계 매핑 시
* DB 레벨에서 **FK 제약조건**으로 무결성 보장

### Elasticsearch의 경우

* `postId`가 실제로 존재하는지 **검증하지 않음**
* FK 개념 자체가 없음

따라서 **애플리케이션 레벨에서 Post 존재 여부를 먼저 확인**해야 합니다.

```java
// Post 없이 Comment를 생성하면 고아 Comment가 됨
Comment orphan = new Comment("non-existent-post-id", "content", "author");
commentRepository.save(orphan); // 에러 없이 저장됨!
```

---

## 작업 2: BaseInitData work6 확장

* Comment가 없을 경우 **5개의 샘플 Comment 생성**
* 각 Comment는 **새 Post와 연결**
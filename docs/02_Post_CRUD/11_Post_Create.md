# Post 생성 기능 구현 (Create)
- 커밋 [c011](https://github.com/farrar142-examples/p-14646-1/commit/c011)
- 트리 [c011](https://github.com/farrar142-examples/p-14646-1/tree/c011)

---

## 작업
### 작업 1: Post 생성자 추가
- title, content, author를 받는 생성자 구현
- createdAt, lastModifiedAt을 OffsetDateTime.now()로 자동 설정

### 작업 2: PostService.create() 메서드 구현
```java
public Post create(String title, String content, String author) {
    Post post = new Post(title, content, author);
    return postRepository.save(post);
}
```
#### ⚠️ JPA와의 차이점 - 영속성 컨텍스트 없음
- JPA: `save()` 후 영속성 컨텍스트에서 관리되며, 같은 트랜잭션 내에서 변경 감지(Dirty Checking)로 자동 저장
- Elasticsearch: 영속성 컨텍스트가 없으므로 변경 시마다 `save()` 호출 필수

```java
// JPA - 변경 감지로 자동 저장
Post post = postRepository.findById(id).get();
post.setTitle("new title"); // 트랜잭션 종료 시 자동 UPDATE

// Elasticsearch - 명시적 save() 필요
Post post = postRepository.findById(id).get();
post.setTitle("new title");
postRepository.save(post); // 반드시 호출해야 저장됨
```

### 작업 3: BaseInitData에 샘플 데이터 생성 로직 추가
- Post가 없을 경우(`count() == 0`) 10개의 샘플 Post 생성
- 개발/테스트 환경에서 초기 데이터 확보 목적
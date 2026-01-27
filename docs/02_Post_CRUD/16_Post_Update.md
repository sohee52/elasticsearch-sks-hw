# Post 수정 기능 구현 (Update)
- 커밋 c016
- 트리 c016

## 작업
### 작업 1: Post Document에 @Data 적용
- @Getter를 @Data로 변경
- @Data는 @Getter, @Setter, @ToString, @EqualsAndHashCode 포함

### 작업 2: PostService.update() 메서드 구현
```java
public Post update(String id, String title, String content) {
    Post post = findById(id);
    if (title != null){
        post.setTitle(title);
    }
    if (content != null){
        post.setContent(content);
    }
    post.setLastModifiedAt(java.time.OffsetDateTime.now());
    return postRepository.save(post);
}
```

### 작업 3: BaseInitData work4 추가
- Post 수정 테스트 로직
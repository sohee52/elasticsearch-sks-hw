# Post 단건 조회 기능 구현 (Read - Single)
- 커밋 [c013](https://github.com/farrar142-examples/p-14646-1/commit/c013)
- 트리 [c013](https://github.com/farrar142-examples/p-14646-1/tree/c013)

## 작업
### 작업 1: PostService.findById() 메서드 구현
```java
public Optional<Post> findById(String id) {
    return postRepository.findById(id);
}
```
- `Optional<Post>` 반환 타입으로 구현
- 존재하지 않는 ID 조회 시 `Optional.empty()` 반환

### 작업 2: BaseInitData work3 추가
- Post 단건 조회 테스트 로직 추가
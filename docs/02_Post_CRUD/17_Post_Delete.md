# Post 삭제 기능 구현 (Delete)
- 커밋 [c017](https://github.com/farrar142-examples/p-14646-1/commit/c017)
- 트리 [c017](https://github.com/farrar142-examples/p-14646-1/tree/c017)

## 작업
### 작업 1: PostService.delete() 메서드 구현
```java
public void delete(String id) {
    Post post = findById(id);
    postRepository.delete(post);
}
```
### 작업 2: BaseInitData work5 추가
- Post 삭제 테스트 로직
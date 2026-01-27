# Post 전체 조회 기능 구현 (Read - List)
- 커밋 [c012](https://github.com/farrar142-examples/p-14646-1/commit/c012)
- 트리 [c012](https://github.com/farrar142-examples/p-14646-1/tree/c012)

---

## 작업
### 작업 1: PostRepository.findAll() 메서드 추가
```java
public interface PostRepository extends ElasticsearchRepository<Post,String> {
    List<Post> findAll();
}
```
- `List<Post> findAll()` 메서드 시그니처 선언
- Spring Data가 자동으로 구현체 생성

>💡 왜 findAll()을 재선언하나요?
> - ElasticsearchRepository의 기본 findAll()은 Iterable<Post>를 반환합니다.
> - List<Post> 반환 타입으로 재선언하면 Spring Data가 자동으로 List로 변환해줍니다.
> - JPA의 JpaRepository는 기본으로 List<T>를 반환하므로 재선언이 필요 없습니다.

### 작업 2: PostService.findAll() 메서드 구현
```java
public List<Post> findAll() {
    return postRepository.findAll();
}
```
- Repository의 findAll() 호출하여 전체 목록 반환

### 작업 3: BaseInitData work2 추가
- 기존 Post 전체 조회 및 로깅으로 저장된 데이터 확인
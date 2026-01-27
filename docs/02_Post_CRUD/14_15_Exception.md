# 커스텀 예외 클래스 생성
- 커밋 [c014](https://github.com/farrar142-examples/p-14646-1/commit/c014)
- 트리 [c014](https://github.com/farrar142-examples/p-14646-1/tree/c014)

## 작업
### 작업 1: DomainException 클래스 생성
```java
public class DomainException extends RuntimeException {
    String resultCode;
    public DomainException(String resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }
}
```
- `RuntimeException` 상속 (Unchecked Exception)
- `resultCode`: 에러 코드 (예: "404", "400")
- `message`: 에러 메시지

### 작업 2: NotFoundException 클래스 생성
```java
public class NotFoundException extends DomainException {
    public NotFoundException(String message) {
        super("404", message);
    }
}
```
- `DomainException` 상속
- 생성자에서 resultCode를 "404"로 고정
- 리소스를 찾을 수 없을 때 사용

---

# Post 단건 조회 예외 처리 적용
- 커밋 [c015](https://github.com/farrar142-examples/p-14646-1/commit/c015)
- 트리 [c015](https://github.com/farrar142-examples/p-14646-1/tree/c015)

## 작업
### 작업 1: Post Document에 @Getter 추가
- Lombok @Getter 어노테이션으로 getter 메서드 자동 생성

### 작업 2: PostService.findById() 예외 처리 개선
```java
public Post findById(String id) {
    return postRepository.findById(id).orElseThrow(()->new NotFoundException("Post not found with id: " + id));
}
```

### 작업 3: BaseInitData work3 개선
- ID로 단건 조회 후 결과 로깅
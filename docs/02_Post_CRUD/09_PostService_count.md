# PostService 생성 및 count 메서드 구현
- 커밋 [c009](https://github.com/farrar142-examples/p-14646-1/commit/c009)
- 트리 [c009](https://github.com/farrar142-examples/p-14646-1/tree/c009)

---

## 작업
### 작업 1: PostService 클래스 생성
```java
@Service
public class PostService {
    public long count() {
        return 0;
    }
}
```
- `@Service` 어노테이션으로 서비스 컴포넌트 지정
- `count()` 메서드 추가 (초기 구현은 0 반환 - 아직 Repository가 없으므로)

### 작업 2: BaseInitData에 PostService 주입
- `@RequiredArgsConstructor`로 생성자 기반 의존성 주입
- 애플리케이션 시작 시 Post 개수를 로깅하여 연결 확인
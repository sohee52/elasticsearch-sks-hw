# Jackson 설정
커밋 [c003](https://github.com/farrar142-examples/p-14646-1/commit/c003)
트리 [c003](https://github.com/farrar142-examples/p-14646-1/tree/c003)

---

## 작업
### 작업 1: Jackson 직렬화 설정 (application.yaml)
```yaml
spring:
  jackson:
    serialization:
      fail-on-empty-beans: false
```
- `spring.jackson.serialization.fail-on-empty-beans`: 빈 객체 직렬화 시 예외 발생 방지 (`false`)
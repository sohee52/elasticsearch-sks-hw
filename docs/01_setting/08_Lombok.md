# Lombok 의존성 추가
- 커밋 [c008](https://github.com/farrar142-examples/p-14646-1/commit/c008)
- 트리 [c008](https://github.com/farrar142-examples/p-14646-1/tree/c008)

---

## 작업
### 작업 1: Lombok 의존성 추가 (build.gradle.kts)
- `compileOnly("org.projectlombok:lombok")`
- `annotationProcessor("org.projectlombok:lombok")`

### 작업 2: BaseInitData에 Lombok 적용
```java
@Configuration
@Slf4j
public class BaseInitData {

    @Bean
    public ApplicationRunner baseInitDataRunner (){
        return args->{
            log.debug("ApplicationRunner 빈은 스프링에 등록되면 자동으로 실행됩니다");
        };
    }
}
```
- `@Slf4j` 어노테이션 추가로 로깅 기능 활성화
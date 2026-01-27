# 초기 데이터 설정 클래스 생성
- 커밋 [c007](https://github.com/farrar142-examples/p-14646-1/commit/c007)
- 트리 [c007](https://github.com/farrar142-examples/p-14646-1/tree/c007)

---

## 작업
### 작업 1: BaseInitData 클래스 생성
```java
@Configuration
public class BaseInitData {

    @Bean
    public ApplicationRunner baseInitDataRunner (){
        return args->{
            System.out.println("ApplicationRunner 빈은 스프링에 등록되면 자동으로 실행됩니다");
        };
    }
}
```
- `@Configuration` 어노테이션으로 설정 클래스 지정
- `ApplicationRunner` Bean을 통해 애플리케이션 시작 시 초기화 로직 실행
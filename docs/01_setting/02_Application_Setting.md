## 애플리케이션 기본 설정  
**커밋:** [c002](https://github.com/farrar142-examples/p-14646-1/commit/c002)  
**트리:** [c002](https://github.com/farrar142-examples/p-14646-1/tree/c002)  

---

## 작업

### 작업 1: 애플리케이션 설정 (`application.yaml`)

```yaml
spring:
  application:
    name: back
  output:
    ansi:
      enabled: ALWAYS

logging:
  level:
    com:
      back: debug

server:
  port: 8080
````

#### 설정 항목 설명

* **spring.application.name**
  애플리케이션 이름을 `back`으로 설정

* **spring.output.ansi.enabled**
  콘솔 출력 시 ANSI 컬러를 항상 활성화 (`ALWAYS`)

* **logging.level.com.back**
  `com.back` 패키지의 로그 레벨을 `debug`로 설정

* **server.port**
  서버 포트를 `8080`으로 지정

```
```

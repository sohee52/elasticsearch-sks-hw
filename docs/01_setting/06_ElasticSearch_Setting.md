# 개발 환경 Elasticsearch 연결 설정
커밋 [c006](https://github.com/farrar142-examples/p-14646-1/commit/c006)
트리 [c006](https://github.com/farrar142-examples/p-14646-1/tree/c006)

---

## 작업
### 작업 1: 개발 환경 설정 (application-dev.yaml)
```yaml
spring:
  elasticsearch:
    uris: localhost:9200
```
- `spring.elasticsearch.uris`: `localhost:9200`으로 Elasticsearch 연결 주소 설정
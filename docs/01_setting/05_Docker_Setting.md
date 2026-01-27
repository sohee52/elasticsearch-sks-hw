# Docker 환경 설정
커밋 [c005](https://github.com/farrar142-examples/p-14646-1/commit/c005)
트리 [c005](https://github.com/farrar142-examples/p-14646-1/tree/c005)

## 작업
### 작업 1: Dockerfile 작성
```
FROM elasticsearch:9.2.2
RUN bin/elasticsearch-plugin install analysis-nori
```
- Elasticsearch 9.2.2 이미지 기반
- `analysis-nori` 플러그인 설치 (한국어 형태소 분석기)

### 작업 2: Docker Compose 설정 (`compose.yml`)
```yaml
services:
  elasticsearch:
    build: .
    image: elasticsearch:nori
    ports:
      - "9200:9200"
    environment:
      - discovery.type=single-node
```
- Elasticsearch 서비스 구성
- 포트 `9200` 노출
- `discovery.type=single-node`: 단일 노드 모드
- `xpack.security.enabled=false`: 보안 기능 비활성화
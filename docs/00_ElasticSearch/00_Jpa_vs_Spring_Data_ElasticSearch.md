## 📌 JPA vs Spring Data Elasticsearch 핵심 차이점

JPA에 익숙한 개발자를 위한 **Spring Data Elasticsearch 핵심 비교표**

| 개념 | JPA (RDB) | Spring Data Elasticsearch |
|---|---|---|
| 어노테이션 | `@Entity` | `@Document` |
| ID 타입 | `Long`, `UUID` 등 | 보통 `String` (Elasticsearch 기본) |
| 테이블 / 인덱스 | `@Table(name = "...")` | `@Document(indexName = "...")` |
| 컬럼 매핑 | `@Column` | `@Field(type = FieldType.xxx)` |
| Repository | `JpaRepository<T, ID>` | `ElasticsearchRepository<T, ID>` |
| 관계 매핑 | `@OneToMany`, `@ManyToOne` 등 | ❌ 없음 (비정규화 / ID 참조) |
| 트랜잭션 | `@Transactional` | ❌ 없음 (문서 단위 원자성) |
| Lazy Loading | 지원 (`FetchType.LAZY`) | ❌ 없음 |
| 영속성 컨텍스트 | 1차 캐시, 변경 감지 | ❌ 없음 |
| Auto DDL | `spring.jpa.hibernate.ddl-auto` | 인덱스 자동 생성 (기본값) |
| Auditing | `@CreatedDate`, `@LastModifiedDate` | 동일하게 지원 |
| 쿼리 메서드 | `findByXxx` | 동일하게 지원 |
| Native Query | JPQL, Native SQL | Elasticsearch Query DSL |

---

## ⚠️ Elasticsearch에서 주의할 점

- **JOIN이 없다**  
  RDB처럼 테이블 간 JOIN을 할 수 없습니다.  
  → 비정규화하거나 ID로 참조한 뒤 애플리케이션 레벨에서 조합

- **트랜잭션이 없다**  
  여러 문서를 묶어 롤백할 수 없습니다.  
  → 단일 문서 단위로만 원자성 보장

- **Near Real-time**  
  문서 저장 후 즉시 검색되지 않을 수 있습니다.  
  → 약 1초의 `refresh` 간격 존재

- **Text vs Keyword**  
  전문 검색용 `text`와 정확 매칭용 `keyword`를 명확히 구분해서 사용해야 합니다.
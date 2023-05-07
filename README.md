# Module

## 헥사고날 아키텍쳐

![다이어그램](./docs/image/hexagonal-architecture.png)

### 1. domain: 도메인 모듈

```text
- 비지니스 로직에서 해결하고자 하는 도메인 객체들
- 위 도식도에서 Entity에 해당한다.
- 모든 모듈에서 사용하는 DTO 객체의 모음
```

|             | adapter-in | adapter-out | core | port-in | port-out | domain |
|-------------|------------|-------------|------|---------|----------|--------|
| 사용가능한 모듈 여부 | -          | -           | -    | -       | -        | -      |

### 2. core: 코어 모듈

```text
- 비지니스 로직을 관리하는 모듈
- 위 도식도에서 Use Case에 해당한다.
- adapter-out 의 구현체가 필요하여 의존성을 가지나 port-out 모듈의 추상화 객체를 이용하도록 한다.
- 웹 통신 / DB 관련 객체는 가급적 사용을 피한다.
```

|             | adapter-in | adapter-out | core | port-in | port-out | domain |
|-------------|------------|-------------|------|---------|----------|--------|
| 사용가능한 모듈 여부 | -          | O           | -    | O       | O        | O      |

### 3. port-in: Input Port 모듈

```text
- In Port 추상화 객체가 모여있는 모듈
- 의존성 순환 때문에 추상화 객체만 분리 해둔다. 
- core 모듈에서 구현체를 작성하도록 한다.
```

|             | adapter-in | adapter-out | core | port-in | port-out | domain |
|-------------|------------|-------------|------|---------|----------|--------|
| 사용가능한 모듈 여부 | -          | -           | -    | -       | -        | O      |

### 4. port-out: Output Port 모듈

```text
- Output Port 추상화 객체가 모여있는 모듈
- 의존성 순환 때문에 추상화 객체만 분리 해둔다. 
- adapter-out 모듈에서 구현체를 작성하도록 한다.
```

|             | adapter-in | adapter-out | core | port-in | port-out | domain |
|-------------|------------|-------------|------|---------|----------|--------|
| 사용가능한 모듈 여부 | -          | -           | -    | -       | -        | O      |

### 5. adapter-in : Input 어댑터 모듈

```text
- 서비스에 들어오는 요청을 처리하는 구현체
- Web Controller / Kafka Consumer 등이 해당 된다.
```

|             | adapter-in | adapter-out | core | port-in | port-out | domain |
|-------------|------------|-------------|------|---------|----------|--------|
| 사용가능한 모듈 여부 | -          | -           | O    | O       | -        | O      |

### 6. adapter-out : Output 어댑터 모듈

```text
- 서비스의 결과 데이터를 처리하는 구현체
- JPA / Kafka Producer 등이 해당 된다.
```

|             | adapter-in | adapter-out | core | port-in | port-out | domain |
|-------------|------------|-------------|------|---------|----------|--------|
| 사용가능한 모듈 여부 | -          | -           | O    | -       | O        | O      |

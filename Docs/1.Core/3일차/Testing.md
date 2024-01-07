# Spring Framework Testing

- 테스트 종류
    - 단위 테스트
    - 통합 테스트
    - 시스템 테스트
    - 인수 테스트
    - 회귀 테스트 : 배포를 했는데 버그가 있다 - 이 때 테스트 케이스를 작성하면 오류가 발생, 이 후에 코드를 수정 후 다시 테스트

- 테스트에 필요한 것
    - 테스트 프레임워크 (JUnit)
    - Mocking (Mockito)

## Spring Test를 위한 환경 구성

```xml
<dependencies>
    ...
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <!-- scope = test -->
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>RELEASE</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.assertj</groupId>
        <artifactId>assertj-core</artifactId>
        <version>3.22.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>    
```

## 단위 테스트
- DI(의존성 주입)를 지향하기 때문에 웹컨테이너나 다른 컴포넌트에 의존성이 없어 쉽게 단위테스트 가능
  - Interface를 Mock, Anonymous class로 
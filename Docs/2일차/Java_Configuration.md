# Java Configuration

## Java Based Bean Configuration
- spring-javaconfig를 사용하면 beans.xml에서 했던 설정을 Java로 작성할 수 있다.

## Spring JavaConfig Class 작성
- Spring JavaConfig 클래스에는 `@Configuration` 어노테이션을 설정해야 한다. - 자바 설정파일인 것을 선언
- Spring JavaConfig 클래스에는 `@Bean` 어노테이션을 설정한 메서드로 구성된다.

## @Bean (Java Config)
- 메서드의 return type이 Spring bean `type`
- 메서드명이 Spring bean `name`
- 

```java
@Configuration
public class ApplicationConfig {

    @Bean
    public String from() {
        return "Seungjo";
    }
}
```

위의 설정(Configuration)은 다음 XML 설정과 동일
```xml
<bean id="from" class="java.lang.String">
    <constructor-arg type="java.lang.String" value="Seungjo" />
</bean>
```

### Bean 생성 - default method
- default 메세드를 가진 인터페이스를 구현하는 방식으로 두 설정을 합성할 수 있다.
```java
public interface BaseJavaConfig {
    @Bean
    default String dbms() {
        return new String("MYSQL");
    }
}

@Configuration
public class JavaConfig implements BaseJavaConfig {
}
```

## AnnotationConfigApplicationContext
- meta data가 XML에서 Annotation으로 변경되었음!
- `AnnotationConfigApplicationContext`의 생성자 파라미터로 받을 수 있는 클래스는 다음과 같다.
```java
AnnotationConfigApplicationContext(Class<?>... componentClasses)
```
- @Configuration 설정한 클래스
- @Component 설정한 클래스 (Stereo Type)

만약, 특정 패키지 하위의 Component를 스캔을 하고싶다면?
```java
AnnotationConfigApplicationContext(String... basePackages)
```

## Bean Lifecycle

### 암묵적인 destroyMethod
- 빈 클래스에 `public`인 close, shutdown method가 존재하면 자동으로 소멸 callback으로 등록
- 이 동작을 비활성화 하려면 `@Bean(destroyMethod="")`라고 설정하면 된다.
```java
@Bean(destroyMethod="")
public DataSource dataSource() throws NamingException {
    return (DataSource) jndiTemplate.loopup("myDS");
}
```

## Bean Scope
- `@Bean` Annotation을 사용한 경우 `@Scope`을 설정해서 scope을 설정할 수 있다.
```java
    @Bean
    @Scope("prototype")
    public Greeter koreanGreeter() {
        return new KoreanGreeter();
    }
```

## Beam Naming
- 기본적으로 스프링 빈의 이름은 메서드 이름
- `@Bean` annotation의 name 속성으로 변경할 수 있다.
```java
    // 아래 스프링빈의 이름은 koreanGreeter 입니다.
    @Bean
    public Greeter koreanGreeter() {
        return new KoreanGreeter();
    }

    // 아래 스프링빈의 이름은 korean 입니다.
    @Bean(name="korean")
    public Greeter koreanGreeter() {
        return new KoreanGreeter();
    }
```
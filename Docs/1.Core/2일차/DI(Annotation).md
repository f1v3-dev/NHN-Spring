# Spring Framework Dependency Injection (Annotation)

- XML 방식으로 Bean 의존성 주입을 Annotation으로 구현할 수 있음!

## Annotation 기반 설정

```xml
<!-- annotation 설정을 위해 추가-->
<context: annotation-config/> 
```
### @Required
- 반드시 의존성이 주입되어야 함을 강제하는 Annotation
- 주입이 되어있지 않다면 오류가 발생하도록 함.
- spring framework 5부터 deplicated 되었음.
  - deplicated : "사용을 권장하지 않으며 차기 버전에서 삭제될 수 있습니다."
  - But, Legacy application에서는 사용할 수 있으니 기억해두기
- bean.xml에 `RequiredAnnotationBeanPostProcessor`를 bean으로 등록
```xml
<bean class="org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor" />
```

- 의존성 주입이 반드시 성공해야 한다는 보장이 필요한 setter method에 `@Required` 설정
```java
public class GreetingService {
    private Greeter greeter;

    @Required
    public void setKoreanGreeter(Greeter greeter) {
        System.out.println("setGreeter invoked!");
        this.greeter = greeter;
    }
    
    ...
}
```

> Annotation 안에 있는 `@Target` : 이 어노테이션을 사용할 수 있는 위치, 범위

## @Autowired
- XML의 autowire 속성을 대신할 수 있음
```java
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    boolean required() default true;
}
```
- Constructor Injection, Setter Injection, Field Injection 모두 가능

### @Autowired - Constructor Injection
- Spring 4.3부터 **생성자 1개인 경우 생략 가능**한 관례가 존재
- Spring Framework에서 권장하는 생성자 방식으로 의존성 주입
```java
public class GreetingService {
    private final Greeter greeter;

    @Autowired
    public GreetingService(Greeter greeter) {
        this.greeter = greeter;
    }

    public void greet() {
        greeter.sayHello();
    }
}
```


### @Autowired - Setter Injection

```java
public class GreetingService {
  private Greeter greeter;

  @Autowired
  public void setKoreanGreeter(Greeter greeter) {
    System.out.println("setGreeter invoked!");
    this.greeter = greeter;
  }

  public void greet() {
    greeter.sayHello();
  }
}
```

### @Autowired - Field Injection
- setter 메서드가 없더라도 field에 직접 `@Autowired` 설정하여 의존성 주입 가능
```java
public class GreetingService {
    @Autowired
    private Greeter greeter;

    public void greet() {
        greeter.sayHello();
    }
}
```

### @Autowired - Collection 
- 같은 타입의 객체가 여러개 존재하는 경우 배열이나 Collection으로 의존성 주입 가능
```java
public class GreetingService {
    private final List<Greeter> greeters;

    @Autowired
    public GreetingService(List<Greeter> greeters) {
        // EnglishGreeter, KoreanGreeter 스프링 빈이 주입 됨.
        // 순서는 알 수 없다.
        this.greeters = greeters;
    }

    public void greet() {
        greeters.forEach(Greeter::sayHello);
    }
}
```

## @Autowired 미세 조정

### required 속성
- 다음과 같이 설정하면 `@Required`와 같은 효과를 볼 수 있음.
- 기본 값은 true
```java
@Autowired(required = true)
```

### Optional Type
- 스프링 빈을 Optional 타입으로 받으면 자동으로 `required = false`로 설정됨


### primary 속성
- Bean 설정에 primary를 설정하면 같은 타입에서도 우선순위를 지정해줄 수 있음.
```xml
   <bean id="koreanGreeter"
         class="com.nhn.edu.springframework.ioc.helloworld.KoreanGreeter" 
         scope="prototype" primary="true" >
    </bean>
```

### @Qualifier
- `@Autowired`와 같이 사용되며, 속성으로 **Spring Bean Name**을 넣어줘야 한다.
```java
    @Autowired
    public GreetingService(@Qualifier("englishGreeter") Greeter greeter) {
        this.greeter = greeter;
    }
```


## Custom @Qualifier
- Annotation을 Custom해서 사용해보자
```java
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(Retentionpolicy.RUNTIME)
@Qualifier
public @interface Lang {
    String value();
}
```

- 위와 같이 선언한 `@Lang` Annotation은 `@Qualifier` 역할을 동일하게 할 수 있다.
```java
@Autowired
public GreetingService(@Lang("englishGreeter") Greeter greeter) {
    this.greeter = greeter;
}
```

- 만약 `@Lang` 어노테이션에 String value()와 같이 값을 입력받지 않게 하고싶다면?
- bean.xml에서 설정을 해줘야 한다.

```xml
<!-- bean.xml -->
<bean id="englishGreeter" class="com.nhnacademy.edu.springframework.greeting.service.EnglishGreeter" scope="singleton">
  <qualifier type="com.nhnacademy.edu.springframework.greeting.annotation.Lang"/>
</bean>
```

## @Value
- `@Value`는 주로 외부 속성을 주입하기 위해서 사용
- ex. DEV Server, Real Server, File Path와 같이 설정해주기 위해서 주로 사용
- `src/main/resource/xxx.properties` 와 같은 경로와 파일명을 가짐

```properties
# greeter.properties
from=Seungjo
```

- beans.xml
```xml
<beans>
  ...
  <context:property-placeholder location="classpath:greeter.properties"/>
</beans>
```

- 이렇게 설정한 속성을 주입하고 싶다면 `@Value` 어노테이션을 사용
```java
public class GreetingService {
    private final Greeter greeter;

    @Value{"${from}"}
    private String from; // <- from 변수에 Seungjo 라는 값이 들어감.
    
    ...
}
```

## Bean Dependencies

### 메서드 파라미터 전달
```java
public class JavaConfig {
    
    @Bean
    public ARepository aRepository() {
        return new ARepositoryImpl();
    }
    
    @Bean
    public AService aService(ARepository aRepository) {
        return new AService(aRepository);
    }
    
    // 인자(Parameter)로 전달하는 방식
}
```

### 메서드 호출
```java
public class JavaConfig {
    
    @Bean
    public ARepository aRepository() {
        return new ARepositoryImpl();
    }
    
    @Bean
    public AService aService() {
        return new AService(aRepository());
    }
}
```

### ⚠️ 주의!!
```java
@Configuration
public class JavaConfig {
    @Bean
    @Scope("singleton")
    public ARepository aRepository() {
        return new ARepositoryImpl();
    }

    @Bean
    public AService aService() {
        return new AService(aRepository());
    }
    
    @Bean
    public BService bService() {
        return new BService(aRepository());
    }
}
```
- ARepository Bean은 Singleton scope을 가지고 있기 때문에 1개만 만들어짐
- `@Configuration` 에서 의존성 주입을 위해서 호출된 메서드는 CGLIB 기술을 사용 -> scope에 따라 스프링 빈을 반환


## @Conditional 
- 조건에 따라 @Configuration이나 @Bean이 동작하지 않도록 설정할 수 있음.
- @Conditional은 Condition 인터페이스 구현을 설정해야 한다.
- Condition interface - matches 메서드 제공, 반환 값이 true이면 설정 동작, false이면 동작 X
- @Profile 어노테이션이 대표적인 예


## Bean Scanning / Component Scanning
- Bean Scanning = Component Scanning = Classpath Scanning

- @Configuration 을 지정한 클래스에 @ComponentScan 을 설정하여 Scanning 을 활성화 할 수 있다.
```java
@Configuration
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework.greeting")
public class BeanConfig {
  ...
}
```

- 그렇다면 스프링 빈으로 등록할 대상은 누가 되는가? - `Stereotype annotations`

### Bean Scanning 대상 (Stereotype Annotations)
- `@Configuration`
- `@Component` : 기본 스프링 관리 컴포넌트
- `@Controller` : Spring Web MVC의 Controller (Spring MVC에서 View를 담당하는 클래스인 경우)
- `@Service` : Service layer의 컴포넌트 marker (비즈니스 로직을 담당하는 클래스인 경우)
- `@Repository` : Data Access Obejct를 의미하는 marker Annotation (데이터를 관리하는 클래스인 경우)


## Component 내부에서 Bean 사용
- @Component 클래스에서도 @Configuration과 동일하게 @Bean을 선언할 수 있음.
- @Configuration 클래스와 동일하게 @Scope, @Qualifier, @Lazy 등을 사용할 수 있음.
```java
@Component
public class FactoryMethodComponent {

    @Bean
    @Qualifier("public")
    public TestBean publicInstance() {
        return new TestBean("publicInstance");
    }

    public void doWork() {
        // Component method implementation omitted
    }
}
```

@Bean vs Stereotype Annotation
둘의 목적은 다르다.
공통으로 사용해야하는 경우 (DbConnection Pool) - 설정 파일에다가 한 곳에다가 모아놓으면 시스템 전체에서 사용한다면
JavaConfig.java - @Bean으로 등록

Stereotype Annotation (@Component)
- 비즈니스 로직
- 공통으로 사용될 일은 극히 드문 경우
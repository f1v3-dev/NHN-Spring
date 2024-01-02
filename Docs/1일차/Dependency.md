# 의존성 (Dependency)

## 정의
- 두 모듈간의 연결을 의존성이라고 한다.
- 객체지향언어에서 두 클래스 간의 관계를 말하기도 한다.

## 의존성의 종류
### 1. 의존 관계 (Dependency)
- A 클래스가 B 클래스를 일시적으로 참조하는 형태

```java
public class B {
    private int numB;
    
    public int getNumB() {
        return this.numB;
    }
}

public class A {
    private int numA;
    
    // add 메서드가 반환한 이후에는 B 클래스의 b 객체는 제거됨.
    public int add(B b) {
        return numA + b.getNumB();
    }
}
```

### 2. 연관 관계 (Association)
- 클래스 필드로 다른 클래스의 객체를 가지고 있는 관계

```java
public class B {
    private int numB;
    
    public int getNumB() {
      return this.numB;
    }
}

public class A {
    private int numA;
    private B b;
    
    // add 메소드가 반환한 이후에도 B 클래스의 b 객체는 여전히 남아 있다.
    public int add() {
      return numA + this.b.getNumB();
    }
}
```

### 3. 집합 관계 (Aggregation)
- 연관 관계의 특수 형태
- A 클래스와 B 클래스의 생명주기는 반드시 일치하지 않는다.
```java
public class B {
    private int numB;
    
    public int getNumB() {
      return this.numB;
    }
}

public class A {
    private int numA;
    private B b;
    
    public A(B externalB) {
        this.b = externalB;
    }
}
```

### 4. 합성 관계 (Composition)
- 연관 관계의 특수 형태
- 집합 관계보다 강결합
- A 클래스와 B 클래스의 생명주기 일치

```java
public class B {
}

public class A {
    private B b;
    
    public A(B externalB) {
        this.b = new B();
    }
}
```


> 위의 모든 의존성 관계를 모두 Dependency(의존성) 이라고 통칭한다.
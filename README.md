# InstanceFactory

Android java library for keeping instances

## How to get it

### Get it from jitpack
[![](https://jitpack.io/v/ranapat/instancefactory.svg)](https://jitpack.io/#ranapat/instancefactory)

## Requirements
* Java 8
* Android SDK
* Gradle

## Building

Build tool is gradle

### Assemble
Run `./gradlew assemble`

### Run unit tests
Run `./gradlew test`

### Run lint
Run `./gradlew lint`

### Run jacoco tests
Run `testDebugUnitTestCoverage`

### Outputs
You can find the outputs here:
- for the lint `./app/build/reports/lint-results-developmentDebug.html`
- for the unit test coverage `./app/build/reports/jacoco/testDevelopmentDebugUnitTestCoverage/html/index.html`
- for the unit test summary `./app/build/reports/tests/testDevelopmentDebugUnitTest/index.html`

### Examples

Get instance

```java
ClassA classA = InstanceFactory.get(ClassA.class);
```

Set instance

```java
ClassA classA = new ClassA();
InstanceFactory.set(ClassA.class, classA);

ClassA classA1 = InstanceFactory.get(ClassA.class);
ClassA classA2 = InstanceFactory.get(ClassA.class);
```

Remove instance

```java
InstanceFactory.remove(ClassA.class);
```

Clear all cached instances

```java
InstanceFactory.clear();
```

Mark static class getter

```java

@StaticallyInstantiable
public class ClassB {
    private static ClassB instance;
    public static ClassB getInstance() {
        if (instance == null) {
            instance = new ClassB();
        }
        return instance;
    }
    private ClassB() {
        //
    }
}

ClassB classB1 = InstanceFactory.get(ClassB.class);
```
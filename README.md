# InstanceFactory

Android java library for keeping instances

## How to get it

### Get it from jitpack
[![](https://jitpack.io/v/ranapat/instancefactory.svg)](https://jitpack.io/#ranapat/instancefactory)

## Requirements
* Java 8
* Android SDK
* Gradle

## Limitations
So far we do not support construction parameters

## Building
Build tool is gradle

### Assemble
Run `./gradlew assemble`

### Run unit tests
Run `./gradlew test`

### Run lint
Run `./gradlew lint`

### Run jacoco tests
Run `./gradlew testReleaseUnitTestCoverage`

### Outputs
You can find the outputs here:
- for the lint
`./instancefactory/build/reports/lint-results-developmentDebug.html`
- for the unit test coverage
`./instancefactory/build/reports/jacoco/testReleaseUnitTestCoverage/html/index.html`
- for the unit test summary
`./instancefactory/build/reports/tests/testReleaseUnitTestCoverage/index.html`

### Examples

Fast access

```java
Fi.get(ClassA.class);
// Fi instead of InstanceFactory
```

Get instance

```java
ClassA classA = InstanceFactory.get(ClassA.class);
```

Set instance

```java
ClassA classA = new ClassA();
InstanceFactory.set(classA, ClassA.class);

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
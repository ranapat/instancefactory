# InstanceFactory

[![Coverage Status](https://coveralls.io/repos/github/ranapat/instancefactory/badge.svg?branch=master)](https://coveralls.io/github/ranapat/instancefactory?branch=master)

Android Java / Kotlin library for keeping instances.

You can keep instances of classes with or without parameters in constructors and different parameter sets will be kept separately.

Class(String 'one') != Class(String 'two')

## Live example

Easy injection pattern can be seen [here](https://github.com/ranapat/simpledependencyinjecting)

## How to get it

### Get it from jitpack
[![](https://jitpack.io/v/ranapat/instancefactory.svg)](https://jitpack.io/#ranapat/instancefactory)
[![](https://jitci.com/gh/ranapat/instancefactory/svg)](https://jitci.com/gh/ranapat/instancefactory)

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

Kotlin lazy

```kotlin
private val instanceH1: ClassH by lazyInject()
private val classJ5: ClassJ by lazyInject("param1", param2, param3, ...)
```

Kotlin direct

```kotlin
val classJ1 = inject<ClassJ>()
val classJ2:ClassJ = inject()
val classJ3:ClassJ = inject("passed")
```

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
// or
InstanceFactory.set(classA);

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
@Static
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
// or
ClassB classB1 = Fi.get(ClassB.class);
```

Mark static class getter - custom getInstance method

```java
@Static(method = "getInstanceCustom")
public class ClassB {
    private static ClassB instance;
    public static ClassB getInstanceCustom() {
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
// or
ClassB classB1 = Fi.get(ClassB.class);
```

Dynamically Initialisable - Injections - Explicit inject

```java
class ClassC {
    @Inject
    private final ClassA value1 = null;

    public ClassC() {
        ...

        InstanceFactory.inject(this);
    }
}

ClassC classC = new Class();
```

Dynamically Initialisable - Injections - Implicit inject

```java
class ClassC {
    @Inject
    private final ClassA value1 = null;

    public ClassC() {
        ...
    }
}

ClassC classC = InstanceFactory.get(ClassC.class);
// or
ClassC classC = Fi.get(ClassC.class);
```

Dynamically Initialisable - Injections - Implicit inject - Custom type

```java
class ClassC {
    @Inject(type = ExtraOnTopOfClassA.class)
    private final ClassA value1 = null;

    public ClassC() {
        ...
    }
}

ClassC classC = InstanceFactory.get(ClassC.class);
// or
ClassC classC = Fi.get(ClassC.class);
```

Dynamically Initialisable - Injections - Implicit inject - Custom type - Works with WeakReference

```java
class ClassC {
    @Inject(type = ExtraOnTopOfClassA.class)
    private final WeakReference<ClassA> value1 = null;

    public ClassC() {
        ...
    }

    public ClassA getValue1() {
        return value1.get();
    }
}

ClassC classC = InstanceFactory.get(ClassC.class);
// or
ClassC classC = Fi.get(ClassC.class);
```

Manual Inject only - prevent get to try to inject - you inject only if you want

```java
@ManualInjectOnly
public class InstanceToDynamicallyInitiliseV7 {
    @Inject
    private TestInstance value1;
    private int value2;

    public InstanceToDynamicallyInitiliseV7() {
        InstanceFactory.inject(this);
    }

    public TestInstance getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
}
```

Debug Feedback

```java
debugFeedback = new DebugFeedback() {
    @Override
    public void attachMap(final Map<String, Object> map) {
        //
    }

    @Override
    public void handlePut(final String key, final Object value) {
        //
    }

    @Override
    public void handleGet(final String key) {
        //
    }

    @Override
    public void handleRemove(final String key) {
        //
    }

    @Override
    public void handleClear() {
        //
    }

    @Override
    public void handleInject(final Object instance) {
        //
    }
};

InstanceFactory.setDebugFeedback(debugFeedback);
InstanceFactory.resetDebugFeedback();
```
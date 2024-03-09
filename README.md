# InstanceFactory

[![Coverage Status](https://coveralls.io/repos/github/ranapat/instancefactory/badge.svg?branch=master)](https://coveralls.io/github/ranapat/instancefactory?branch=master)
[![](https://jitci.com/gh/ranapat/instancefactory/svg)](https://jitci.com/gh/ranapat/instancefactory)

Android Java / Kotlin library for keeping instances.

You can keep instances of classes with or without parameters in constructors and different parameter sets will be kept separately.

Class(String 'one') != Class(String 'two')

## Features

- auto creating and keeping instances by classname
- auto creating and keeping instances by classname and parameters
- auto creating and keeping instances by classname, parameters and namespaces ![new](./documentation/assets/new_24x24.png)
- setting instances manually that can be reused later
- runtime clearing of piled up instances for advanced memory management
- lazy kotlin helpers

## Live example

Easy injection pattern can be seen [here](./app/src/main/java/org/ranapat/instancefactory/example)

## How to get it

### Get it from jitpack
[![](https://jitpack.io/v/ranapat/instancefactory.svg)](https://jitpack.io/#ranapat/instancefactory)

### Get it from source
Check out the repo and have fun

## Requirements
* Java 11
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
Run `./gradlew testDebugUnitTestCoverage` or `./gradlew testReleaseUnitTestCoverage` or `./scripts/tests`

### Outputs
You can find the outputs here:
- for the lint
`./instancefactory/build/reports/lint-results-developmentDebug.html`
- for the unit test coverage
`./instancefactory/build/reports/jacoco/testDebugUnitTestCoverage/html/index.html` or `./instancefactory/build/reports/jacoco/testReleaseUnitTestCoverage/html/index.html`
- for the unit test summary
`./instancefactory/build/reports/tests/testDebugUnitTestCoverage/index.html` or `./instancefactory/build/reports/tests/testReleaseUnitTestCoverage/index.html` 

## Join the project
If you find this project interesting check out the ongoing
[issues](https://github.com/ranapat/instancefactory/issues) and add your ides.

## Quick how-to

Kotlin lazy

```kotlin
private val instanceH1: ClassH by lazyInject()
private val classJ5: ClassJ by lazyInject("param1", param2, param3, ...)
// or
private val instanceH1: ClassH by lazyGet()
private val classJ5: ClassJ by lazyGet("param1", param2, param3, ...)
// or
private val namespaceA: Namespace = object : Namespace() {}
private val instanceH1: ClassH by lazyGet(namespaceA)
private val classJ5: ClassJ by lazyGet(namespaceA"param1", param2, param3, ...)
```

Kotlin direct

```kotlin
val classJ1 = inject<ClassJ>()
val classJ2:ClassJ = inject()
val classJ3:ClassJ = inject("passed")
// or
val classJ1 = get<ClassJ>()
val classJ2:ClassJ = get()
val classJ3:ClassJ = get("passed")
// or
private val namespaceA: Namespace = object : Namespace() {}
val classJ1 = get<ClassJ>(namespaceA)
val classJ2:ClassJ = get(namespaceA)
val classJ3:ClassJ = get(namespaceA"passed")
```

Java Fast access

```java
Fi.get(ClassA.class);
// or
static final Namespace namespaceA = new Namespace() {};
Fi.get(namespaceA, ClassA.class);
```

Java Detailed access

```java
ClassA classA = InstanceFactory.get(ClassA.class);
// or
static final Namespace namespaceA = new Namespace() {};
ClassA classA = InstanceFactory.get(namespaceA, ClassA.class);
```

For more check [here](./documentation/examples/index.md).
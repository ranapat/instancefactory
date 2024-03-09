# InstanceFactory offers several `Java` ways to work with instances

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

Set instance

```java
ClassA classA = new ClassA();
InstanceFactory.set(classA, ClassA.class);
// or
InstanceFactory.set(classA);
// or
static final Namespace namespaceA = new Namespace() {};
InstanceFactory.set(namespaceA, classA);
// or
static final Namespace namespaceA = new Namespace() {};
InstanceFactory.set(namespaceA, classA, ClassA.class);

ClassA classA1 = InstanceFactory.get(ClassA.class);
ClassA classA2 = InstanceFactory.get(ClassA.class);
// or
static final Namespace namespaceA = new Namespace() {};
ClassA classA1 = InstanceFactory.get(namespaceA, ClassA.class);
ClassA classA2 = InstanceFactory.get(namespaceA, ClassA.class);
```

Remove instance

```java
InstanceFactory.remove(ClassA.class);
// or
static final Namespace namespaceA = new Namespace() {};
InstanceFactory.remove(namespaceA, ClassA.class);
```

Clear all cached instances

```java
InstanceFactory.clear();
static final Namespace namespaceA = new Namespace() {};
InstanceFactory.clear(namespaceA);
InstanceFactory.clearAll();
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
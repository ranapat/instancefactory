package org.ranapat.instancefactory;

import org.junit.After;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class InstanceFactoryTest {

    @After
    public void after() {
        InstanceFactory.clear();
    }

    @Test
    public void initializeDynamicallyInitialisableFields() {
        final InstanceToDynamicallyInitiliseV1 instance1 = new InstanceToDynamicallyInitiliseV1();
        final InstanceToDynamicallyInitiliseV2 instance2 = new InstanceToDynamicallyInitiliseV2();
        final InstanceToDynamicallyInitiliseV3 instance3 = new InstanceToDynamicallyInitiliseV3();

        assertThat(instance1.getValue1(), is(equalTo(null)));
        assertThat(instance2.getValue1(), is(equalTo(null)));
        assertThat(instance3.getValue1(), is(equalTo(null)));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance2.getValue2(), is(equalTo(0)));
        assertThat(instance3.getValue2(), is(equalTo(0)));

        InstanceFactory.initialise(instance1);
        InstanceFactory.initialise(instance2);
        InstanceFactory.initialise(instance3);

        assertThat(instance1.getValue1(), is(not(equalTo(null))));
        assertThat(instance2.getValue1(), is(equalTo(null)));
        assertThat(instance3.getValue1(), is(not(equalTo(null))));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance2.getValue2(), is(equalTo(0)));
        assertThat(instance3.getValue2(), is(equalTo(0)));
        assertThat(instance1.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance3.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance1.getValue1(), is(equalTo(instance3.getValue1())));
    }

    @Test
    public void gettingNotPresentInstanceShouldProduceNewInstance() {
        String instance = InstanceFactory.get(String.class);

        assertThat(instance, is(not(nullValue())));
    }

    @Test
    public void gettingTwoTimesSameClassInstanceShouldReturnSameInstance() {
        String instance1 = InstanceFactory.get(String.class);
        String instance2 = InstanceFactory.get(String.class);

        assertThat(instance1, is(sameInstance(instance2)));
    }

    @Test
    public void gettingInstancesWithParametersShallCache() {
        String instance1 = InstanceFactory.get(String.class, new Class[]{String.class}, "something");
        String instance2 = InstanceFactory.get(String.class, new Class[]{String.class}, "something");

        assertThat(instance1, is(sameInstance(instance2)));
    }

    @Test
    public void gettingInstancesWithDifferentParametersShallDiffer() {
        String instance1 = InstanceFactory.get(String.class, new Class[]{String.class}, "something1");
        String instance2 = InstanceFactory.get(String.class, new Class[]{String.class}, "something2");

        assertThat(instance1, is(not(sameInstance(instance2))));
    }

    @Test
    public void setInstanceForClassShouldWork() {
        String instance = "test";
        InstanceFactory.set(instance, String.class);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance)));
    }

    @Test
    public void setInstanceForClassWithParametersShouldWork() {
        String instance = "test";
        InstanceFactory.set(instance, String.class, new Class[]{String.class}, "test");

        assertThat(InstanceFactory.get(String.class, new Class[]{String.class}, "test"), is(sameInstance(instance)));
    }

    @Test
    public void removingInstanceAndGettingNewOneWorks() {
        String instance = "test";
        InstanceFactory.set(instance, String.class);
        InstanceFactory.remove(String.class);

        String instance2 = InstanceFactory.get(String.class);

        assertThat(instance, is(not(sameInstance(instance2))));
    }

    @Test
    public void removingInstanceWithParametersAndGettingNewOneWorks() {
        String instance = "test";
        InstanceFactory.set(instance, String.class, new Class[]{String.class}, "test");
        InstanceFactory.remove(String.class, new Class[]{String.class}, "test");

        String instance2 = InstanceFactory.get(String.class, new Class[]{String.class}, "test");

        assertThat(instance, is(not(sameInstance(instance2))));
    }

    @Test
    public void clearingInstanceFactoryShouldLeadToAllInstanceBeingCreatedFreshly() {
        TestInstance integerInstance1 = InstanceFactory.get(TestInstance.class);
        String stringInstance1 = InstanceFactory.get(String.class);

        InstanceFactory.clear();

        TestInstance integerInstance2 = InstanceFactory.get(TestInstance.class);
        String stringInstance2 = InstanceFactory.get(String.class);

        assertThat(integerInstance1, is(not(sameInstance(integerInstance2))));
        assertThat(stringInstance1, is(not(sameInstance(stringInstance2))));
    }

    @Test
    public void instanceOfClassWithoutDefaultConstructorShouldLeadToNullValue() {
        TestClassWithPrivateConstructor testClassWithPrivateConstructor = InstanceFactory.get(TestClassWithPrivateConstructor.class);

        assertThat(testClassWithPrivateConstructor, is(nullValue()));
    }

    @Test
    public void instanceOfAbstractClassShouldBeNull() {
        TestAbstractClass testAbstractClass = InstanceFactory.get(TestAbstractClass.class);
        assertThat(testAbstractClass, is(nullValue()));
    }

    @Test
    public void shouldReturnNullInWrongInput() {
        assertThat(InstanceFactory.get(String.class, new Class[]{String.class}), is(equalTo(null)));
    }

    @Test
    public void shouldNotBeCreatedDirectly() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<InstanceFactory> constructor = InstanceFactory.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers()), is(equalTo(true)));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void shallWorkWithStaticallyMarkedWithoutParameters() {
        StaticallyMarkedA staticallyMarked1 = InstanceFactory.get(StaticallyMarkedA.class);
        assertThat(staticallyMarked1, is(not(nullValue())));

        StaticallyMarkedA staticallyMarked2 = InstanceFactory.get(StaticallyMarkedA.class);
        assertThat(staticallyMarked2, is(equalTo(staticallyMarked2)));
    }

    @Test
    public void shallWorkWithStaticallyMarkedWithParameters() {
        StaticallyMarkedB staticallyMarked1 = InstanceFactory.get(StaticallyMarkedB.class, new Class[]{String.class}, "something");
        assertThat(staticallyMarked1, is(not(nullValue())));

        StaticallyMarkedB staticallyMarked2 = InstanceFactory.get(StaticallyMarkedB.class, new Class[]{String.class}, "something");
        assertThat(staticallyMarked2, is(equalTo(staticallyMarked2)));

        StaticallyMarkedB staticallyMarked3 = InstanceFactory.get(StaticallyMarkedB.class, new Class[]{String.class}, "somethingElse");
        assertThat(staticallyMarked2, is(not(equalTo(staticallyMarked3))));
    }
}

class InstanceToDynamicallyInitiliseV1 {
    @DynamicallyInitialisable
    private TestInstance value1;
    private int value2;

    public TestInstance getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
}
class InstanceToDynamicallyInitiliseV2 {
    private TestInstance value1;
    @DynamicallyInitialisable
    private int value2;

    public TestInstance getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
}
class InstanceToDynamicallyInitiliseV3 {
    @DynamicallyInitialisable
    private final TestInstance value1 = null;
    private int value2;

    public TestInstance getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
}

class TestInstance {
    private String test;

    public TestInstance() {
        this.test = "test";
    }

    @Override
    public String toString() {
        return "TestInstance{" +
                "test='" + test + '\'' +
                '}';
    }
}

class TestClassWithPrivateConstructor {
    private TestClassWithPrivateConstructor() {}
}

@StaticallyInstantiable
class StaticallyMarkedA {
    public static StaticallyMarkedA getInstance() {
        return new StaticallyMarkedA();
    }

    private StaticallyMarkedA() {}
}

@StaticallyInstantiable
class StaticallyMarkedB {
    public static StaticallyMarkedB getInstance(final String parameter) {
        return new StaticallyMarkedB(parameter);
    }

    private StaticallyMarkedB(final String parameter) {}
}

abstract class TestAbstractClass {}
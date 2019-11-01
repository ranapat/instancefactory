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
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class FiTest {

    @After
    public void after() {
        Fi.clear();
    }

    @Test
    public void gettingNotPresentInstanceShouldProduceNewInstance() {
        String instance = Fi.get(String.class);

        assertThat(instance, is(not(nullValue())));
    }

    @Test
    public void gettingTwoTimesSameClassInstanceShouldReturnSameInstance() {
        String instance1 = Fi.get(String.class);
        String instance2 = Fi.get(String.class);

        assertThat(instance1, is(sameInstance(instance2)));
    }

    @Test
    public void setInstanceForClassShouldWork() {
        String instance = "test";
        Fi.set(String.class, instance);

        assertThat(Fi.get(String.class), is(sameInstance(instance)));
    }

    @Test
    public void removingInstanceAndGettingNewOneWorks() {
        String instance = "test";
        Fi.set(String.class, instance);
        Fi.remove(String.class);

        String instance2 = Fi.get(String.class);

        assertThat(instance, is(not(sameInstance(instance2))));
    }

    @Test
    public void clearingInstanceFactoryShouldLeadToAllInstanceBeingCreatedFreshly() {
        TestInstance integerInstance1 = Fi.get(TestInstance.class);
        String stringInstance1 = Fi.get(String.class);

        Fi.clear();

        TestInstance integerInstance2 = Fi.get(TestInstance.class);
        String stringInstance2 = Fi.get(String.class);

        assertThat(integerInstance1, is(not(sameInstance(integerInstance2))));
        assertThat(stringInstance1, is(not(sameInstance(stringInstance2))));
    }

    @Test
    public void instanceOfClassWithoutDefaultConstructorShouldLeadToNullValue() {
        TestClassWithPrivateConstructor testClassWithPrivateConstructor = Fi.get(TestClassWithPrivateConstructor.class);

        assertThat(testClassWithPrivateConstructor, is(nullValue()));
    }

    @Test
    public void instanceOfAbstractClassShouldBeNull() {
        TestAbstractClass testAbstractClass = Fi.get(TestAbstractClass.class);
        assertThat(testAbstractClass, is(nullValue()));
    }

    @Test
    public void shouldNotBeCreatedDirectly() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Fi> constructor = Fi.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers()), is(equalTo(true)));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void shallWorkWithStaticallyMarked() {
        StaticallyMarked staticallyMarked = Fi.get(StaticallyMarked.class);
        assertThat(staticallyMarked, is(not(nullValue())));
    }
}

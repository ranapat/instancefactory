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
        InstanceFactory.clear();
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

package org.ranapat.instancefactory;

import org.junit.After;
import org.junit.Test;
import org.ranapat.instancefactory.tools.StaticallyMarkedA;
import org.ranapat.instancefactory.tools.TestAbstractClass;
import org.ranapat.instancefactory.tools.TestClassWithPrivateConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class FiTest {

    @After
    public void after() {
        InstanceFactory.clear();
    }

    @Test
    public void gettingNotPresentInstanceShouldProduceNewInstance() {
        final String instance = Fi.get(String.class);

        assertThat(instance, is(not(nullValue())));
    }

    @Test
    public void gettingTwoTimesSameClassInstanceShouldReturnSameInstance() {
        final String instance1 = Fi.get(String.class);
        final String instance2 = Fi.get(String.class);

        assertThat(instance1, is(sameInstance(instance2)));
    }

    @Test
    public void instanceOfClassWithoutDefaultConstructorShouldLeadToNullValue() {
        final TestClassWithPrivateConstructor testClassWithPrivateConstructor = Fi.get(TestClassWithPrivateConstructor.class);

        assertThat(testClassWithPrivateConstructor, is(nullValue()));
    }

    @Test
    public void instanceOfAbstractClassShouldBeNull() {
        final TestAbstractClass testAbstractClass = Fi.get(TestAbstractClass.class);
        assertThat(testAbstractClass, is(nullValue()));
    }

    @Test
    public void shouldNotBeCreatedDirectly() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Constructor<Fi> constructor = Fi.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers()), is(equalTo(true)));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void shallWorkWithStaticallyMarked() {
        final StaticallyMarkedA staticallyMarked = Fi.get(StaticallyMarkedA.class);
        assertThat(staticallyMarked, is(not(nullValue())));
    }
}

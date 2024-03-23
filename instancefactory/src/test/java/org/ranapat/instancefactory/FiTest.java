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
    private static final Namespace defaultNamespace = Namespace.DEFAULT;
    private static final Namespace namespaceA = new Namespace() {};
    private static final Namespace namespaceB = new Namespace() {};

    @After
    public void after() {
        InstanceFactory.clearAll();
    }

    @Test
    public void shouldNotBeCreatedDirectly() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Constructor<Fi> constructor = Fi.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers()), is(equalTo(true)));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void gettingUniqueWillNotBeCachedCase1() {
        final String instance1 = Fi.unique(String.class);
        final String instance2 = Fi.get(String.class);
        final String instance3 = Fi.get(String.class);

        assertThat(instance1, is(not(sameInstance(instance2))));
        assertThat(instance2, is(sameInstance(instance3)));
    }

    @Test
    public void gettingUniqueWillNotBeCachedCase2() {
        final String instance1 = Fi.unique(String.class);
        final String instance2 = Fi.get(String.class);
        final String instance3 = Fi.get(String.class);
        final String instance4 = Fi.get(namespaceA, String.class);
        final String instance5 = Fi.get(namespaceA, String.class);

        assertThat(instance1, is(not(sameInstance(instance2))));
        assertThat(instance1, is(not(sameInstance(instance4))));
        assertThat(instance2, is(not(sameInstance(instance4))));
        assertThat(instance2, is(sameInstance(instance3)));
        assertThat(instance4, is(sameInstance(instance5)));
    }

    @Test
    public void gettingNotPresentInstanceProducesNewInstanceDefaultNamespace() {
        final String instance = Fi.get(String.class);

        assertThat(instance, is(not(nullValue())));
    }

    @Test
    public void gettingTwoTimesSameClassInstanceReturnsSameInstanceDefaultNamespace() {
        final String instance1 = Fi.get(String.class);
        final String instance2 = Fi.get(String.class);

        assertThat(instance1, is(sameInstance(instance2)));
    }

    @Test
    public void instanceOfClassWithoutDefaultConstructorLeadsToNullValueDefaultNamespace() {
        final TestClassWithPrivateConstructor testClassWithPrivateConstructor = Fi.get(TestClassWithPrivateConstructor.class);

        assertThat(testClassWithPrivateConstructor, is(nullValue()));
    }

    @Test
    public void instanceOfAbstractClassLeadsToNullDefaultNamespace() {
        final TestAbstractClass testAbstractClass = Fi.get(TestAbstractClass.class);
        assertThat(testAbstractClass, is(nullValue()));
    }

    @Test
    public void staticallyMarkedDefaultNamespace() {
        final StaticallyMarkedA staticallyMarked = Fi.get(StaticallyMarkedA.class);
        assertThat(staticallyMarked, is(not(nullValue())));
    }

    @Test
    public void gettingNotPresentInstanceProducesNewInstanceMultipleNamespaces() {
        final String instance1 = Fi.get(defaultNamespace, String.class);

        assertThat(instance1, is(not(nullValue())));

        final String instance2 = Fi.get(namespaceA, String.class);

        assertThat(instance2, is(not(nullValue())));

        final String instance3 = Fi.get(namespaceB, String.class);

        assertThat(instance3, is(not(nullValue())));

        assertThat(instance1, is(not(sameInstance(instance2))));
        assertThat(instance1, is(not(sameInstance(instance3))));
        assertThat(instance2, is(not(sameInstance(instance3))));
    }

    @Test
    public void gettingTwoTimesSameClassInstanceReturnsSameInstanceMultipleNamespaces() {
        final String instance1 = Fi.get(defaultNamespace, String.class);
        final String instance2 = Fi.get(defaultNamespace, String.class);

        assertThat(instance1, is(sameInstance(instance2)));

        final String instance3 = Fi.get(namespaceA, String.class);
        final String instance4 = Fi.get(namespaceA, String.class);

        assertThat(instance3, is(sameInstance(instance4)));

        final String instance5 = Fi.get(namespaceB, String.class);
        final String instance6 = Fi.get(namespaceB, String.class);

        assertThat(instance5, is(sameInstance(instance6)));
    }

    @Test
    public void instanceOfClassWithoutDefaultConstructorLeadsToNullValueMultipleNamespaces() {
        final TestClassWithPrivateConstructor testClassWithPrivateConstructor = Fi.get(defaultNamespace, TestClassWithPrivateConstructor.class);

        assertThat(testClassWithPrivateConstructor, is(nullValue()));
    }

    @Test
    public void instanceOfAbstractClassLeadsToNullMultipleNamespaces() {
        final TestAbstractClass testAbstractClass = Fi.get(defaultNamespace, TestAbstractClass.class);
        assertThat(testAbstractClass, is(nullValue()));
    }

    @Test
    public void staticallyMarkedMultipleNamespaces() {
        final StaticallyMarkedA staticallyMarked = Fi.get(defaultNamespace, StaticallyMarkedA.class);
        assertThat(staticallyMarked, is(not(nullValue())));
    }
}

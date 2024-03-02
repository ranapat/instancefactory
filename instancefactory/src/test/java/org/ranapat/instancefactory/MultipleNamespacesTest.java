package org.ranapat.instancefactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

import org.junit.After;
import org.junit.Test;
import org.ranapat.instancefactory.tools.TestAbstractClass;
import org.ranapat.instancefactory.tools.TestClassWithPrivateConstructor;

public class MultipleNamespacesTest {
    private static final Namespace defaultNamespace = Namespace.DEFAULT;
    private static final Namespace namespaceA = new Namespace() {};
    private static final Namespace namespaceB = new Namespace() {};

    @After
    public void after() {
        InstanceFactory.clearAll();
    }

    @Test
    public void gettingInstancesInDifferentNamespacesProducesNewInstance() {
        final String instance1 = InstanceFactory.get(String.class);
        final String instance2 = InstanceFactory.get(defaultNamespace, String.class);

        assertThat(instance1, is(sameInstance(instance2)));

        final String instance3 = InstanceFactory.get(namespaceA, String.class);
        final String instance4 = InstanceFactory.get(namespaceA, String.class);

        assertThat(instance3, is(sameInstance(instance4)));

        final String instance5 = InstanceFactory.get(namespaceB, String.class);
        final String instance6 = InstanceFactory.get(namespaceB, String.class);

        assertThat(instance5, is(sameInstance(instance6)));

        assertThat(instance1, is(not(sameInstance(instance3))));
        assertThat(instance1, is(not(sameInstance(instance5))));
        assertThat(instance3, is(not(sameInstance(instance5))));
    }

    @Test
    public void gettingInstancesWithParametersCaches() {
        final String instance1 = InstanceFactory.get(String.class, new Class[]{String.class}, "something");
        final String instance2 = InstanceFactory.get(defaultNamespace, String.class, new Class[]{String.class}, "something");

        assertThat(instance1, is(sameInstance(instance2)));

        final String instance3 = InstanceFactory.get(namespaceA, String.class, new Class[]{String.class}, "something");
        final String instance4 = InstanceFactory.get(namespaceA, String.class, new Class[]{String.class}, "something");

        assertThat(instance3, is(sameInstance(instance4)));

        final String instance5 = InstanceFactory.get(namespaceB, String.class, new Class[]{String.class}, "something");
        final String instance6 = InstanceFactory.get(namespaceB, String.class, new Class[]{String.class}, "something");

        assertThat(instance5, is(sameInstance(instance6)));

        assertThat(instance1, is(not(sameInstance(instance3))));
        assertThat(instance1, is(not(sameInstance(instance5))));
        assertThat(instance3, is(not(sameInstance(instance5))));
    }

    @Test
    public void gettingInstancesWithDifferentParametersDiffers() {
        final String instance1 = InstanceFactory.get(String.class, new Class[]{String.class}, "something1");
        final String instance2 = InstanceFactory.get(defaultNamespace, String.class, new Class[]{String.class}, "something2");

        assertThat(instance1, is(not(sameInstance(instance2))));

        final String instance3 = InstanceFactory.get(namespaceA, String.class, new Class[]{String.class}, "something1");
        final String instance4 = InstanceFactory.get(namespaceA, String.class, new Class[]{String.class}, "something2");

        assertThat(instance3, is(not(sameInstance(instance4))));

        final String instance5 = InstanceFactory.get(namespaceB, String.class, new Class[]{String.class}, "something1");
        final String instance6 = InstanceFactory.get(namespaceB, String.class, new Class[]{String.class}, "something2");

        assertThat(instance5, is(not(sameInstance(instance6))));

        assertThat(instance1, is(not(sameInstance(instance3))));
        assertThat(instance1, is(not(sameInstance(instance4))));
        assertThat(instance1, is(not(sameInstance(instance5))));
        assertThat(instance1, is(not(sameInstance(instance6))));

        assertThat(instance2, is(not(sameInstance(instance3))));
        assertThat(instance2, is(not(sameInstance(instance4))));
        assertThat(instance2, is(not(sameInstance(instance5))));
        assertThat(instance2, is(not(sameInstance(instance6))));

        assertThat(instance3, is(not(sameInstance(instance5))));
        assertThat(instance3, is(not(sameInstance(instance6))));

        assertThat(instance4, is(not(sameInstance(instance5))));
        assertThat(instance4, is(not(sameInstance(instance6))));
    }

    @Test
    public void instanceOfClassWithoutDefaultConstructorLeadsToNullValue() {
        final TestClassWithPrivateConstructor testClassWithPrivateConstructor = InstanceFactory.get(namespaceA, TestClassWithPrivateConstructor.class);

        assertThat(testClassWithPrivateConstructor, is(nullValue()));
    }

    @Test
    public void instanceOfAbstractClassGetsNull() {
        final TestAbstractClass testAbstractClass = InstanceFactory.get(namespaceA, TestAbstractClass.class);

        assertThat(testAbstractClass, is(nullValue()));
    }

    @Test
    public void returnNullInWrongInput() {
        assertThat(InstanceFactory.get(namespaceA, String.class, new Class[]{String.class}), is(equalTo(null)));
    }

}

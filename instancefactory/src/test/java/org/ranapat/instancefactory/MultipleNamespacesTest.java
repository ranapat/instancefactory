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
import org.ranapat.instancefactory.tools.TestInstance;

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

    @Test
    public void setInstanceWithoutClassInDefaultNamespace() {
        final String instance = "test";
        InstanceFactory.set(instance);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance)));
        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(sameInstance(instance)));
        assertThat(InstanceFactory.get(namespaceA, String.class), is(not(sameInstance(instance))));
        assertThat(InstanceFactory.get(namespaceB, String.class), is(not(sameInstance(instance))));
    }

    @Test
    public void setInstanceWithoutClassInDifferentNamespaces() {
        final String instance = "test";
        InstanceFactory.set(instance);
        InstanceFactory.set(namespaceA, instance);
        InstanceFactory.set(namespaceB, instance);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance)));
        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(sameInstance(instance)));
        assertThat(InstanceFactory.get(namespaceA, String.class), is(sameInstance(instance)));
        assertThat(InstanceFactory.get(namespaceB, String.class), is(sameInstance(instance)));
    }

    @Test
    public void setInstanceWithoutClassOverwritesIfSame() {
        final String instance1 = "test1";
        final String instance2 = "test2";
        InstanceFactory.set(instance1);
        InstanceFactory.set(instance2);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance2)));

        InstanceFactory.set(defaultNamespace, instance1);
        InstanceFactory.set(defaultNamespace, instance2);

        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(sameInstance(instance2)));

        InstanceFactory.set(namespaceA, instance1);
        InstanceFactory.set(namespaceA, instance2);

        assertThat(InstanceFactory.get(namespaceA, String.class), is(sameInstance(instance2)));

        InstanceFactory.set(namespaceB, instance1);
        InstanceFactory.set(namespaceB, instance2);

        assertThat(InstanceFactory.get(namespaceB, String.class), is(sameInstance(instance2)));
    }

    @Test
    public void setInstanceForClass() {
        final String instance = "test";
        InstanceFactory.set(instance, String.class);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance)));

        InstanceFactory.set(defaultNamespace, instance, String.class);

        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(sameInstance(instance)));

        InstanceFactory.set(namespaceA, instance, String.class);

        assertThat(InstanceFactory.get(namespaceA, String.class), is(sameInstance(instance)));

        InstanceFactory.set(namespaceB, instance, String.class);

        assertThat(InstanceFactory.get(namespaceB, String.class), is(sameInstance(instance)));
    }

    @Test
    public void setInstanceForClassOverwritesIfSame() {
        final String instance1 = "test1";
        final String instance2 = "test1";
        InstanceFactory.set(instance1, String.class);
        InstanceFactory.set(instance2, String.class);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance2)));

        InstanceFactory.set(defaultNamespace, instance1, String.class);
        InstanceFactory.set(defaultNamespace, instance2, String.class);

        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(sameInstance(instance2)));

        InstanceFactory.set(namespaceA, instance1, String.class);
        InstanceFactory.set(namespaceA, instance2, String.class);

        assertThat(InstanceFactory.get(namespaceA, String.class), is(sameInstance(instance2)));

        InstanceFactory.set(namespaceB, instance1, String.class);
        InstanceFactory.set(namespaceB, instance2, String.class);

        assertThat(InstanceFactory.get(namespaceB, String.class), is(sameInstance(instance2)));
    }

    @Test
    public void setInstanceForClassWithParameters() {
        final String instance = "test";
        InstanceFactory.set(instance, String.class, new Class[]{String.class}, "test");

        assertThat(InstanceFactory.get(String.class, new Class[]{String.class}, "test"), is(sameInstance(instance)));

        InstanceFactory.set(defaultNamespace, instance, String.class, new Class[]{String.class}, "test");

        assertThat(InstanceFactory.get(defaultNamespace, String.class, new Class[]{String.class}, "test"), is(sameInstance(instance)));

        InstanceFactory.set(namespaceA, instance, String.class, new Class[]{String.class}, "test");

        assertThat(InstanceFactory.get(namespaceA, String.class, new Class[]{String.class}, "test"), is(sameInstance(instance)));

        InstanceFactory.set(namespaceB, instance, String.class, new Class[]{String.class}, "test");

        assertThat(InstanceFactory.get(namespaceB, String.class, new Class[]{String.class}, "test"), is(sameInstance(instance)));
    }

    @Test
    public void removingInstanceThatDoesNotExist() {
        InstanceFactory.remove(String.class);
        InstanceFactory.remove(defaultNamespace, String.class);
        InstanceFactory.remove(namespaceA, String.class);
        InstanceFactory.remove(namespaceB, String.class);
    }

    @Test
    public void removingAllSetInstance() {
        final String instance1 = "test";
        final Integer instance2 = 2;

        InstanceFactory.set(instance1);
        InstanceFactory.set(instance2);

        InstanceFactory.remove(String.class);
        InstanceFactory.remove(Integer.class);

        InstanceFactory.set(defaultNamespace, instance1);
        InstanceFactory.set(defaultNamespace, instance2);

        InstanceFactory.remove(defaultNamespace, String.class);
        InstanceFactory.remove(defaultNamespace, Integer.class);

        InstanceFactory.set(namespaceA, instance1);
        InstanceFactory.set(namespaceA, instance2);

        InstanceFactory.remove(namespaceA, String.class);
        InstanceFactory.remove(namespaceA, Integer.class);

        InstanceFactory.set(namespaceB, instance1);
        InstanceFactory.set(namespaceB, instance2);

        InstanceFactory.remove(namespaceB, String.class);
        InstanceFactory.remove(namespaceB, Integer.class);
    }

    @Test
    public void removingInstanceAndGettingNewOneCase1() {
        final String instance = "test";
        InstanceFactory.set(defaultNamespace, instance, String.class);
        InstanceFactory.remove(defaultNamespace, String.class);

        final String instance2 = InstanceFactory.get(defaultNamespace, String.class);

        assertThat(instance, is(not(sameInstance(instance2))));
    }

    @Test
    public void removingInstanceAndGettingNewOneCase2() {
        final String instance1 = "test1";
        final String instance2 = "test2";
        InstanceFactory.set(defaultNamespace, instance1, String.class);
        InstanceFactory.set(namespaceA, instance1, String.class);
        InstanceFactory.set(namespaceB, instance2, String.class);
        InstanceFactory.remove(namespaceA, String.class);

        final String instance3 = InstanceFactory.get(defaultNamespace, String.class);

        assertThat(instance1, is(sameInstance(instance3)));
        assertThat(instance2, is(not(sameInstance(instance3))));

        final String instance4 = InstanceFactory.get(namespaceA, String.class);

        assertThat(instance2, is(not(sameInstance(instance4))));
    }

    @Test
    public void removingInstanceAndGettingNewOneCase3() {
        final String instance1 = "test1";
        InstanceFactory.set(defaultNamespace, instance1, String.class);
        InstanceFactory.set(namespaceA, instance1, String.class);
        InstanceFactory.set(namespaceB, instance1, String.class);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance1)));
        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(sameInstance(instance1)));
        assertThat(InstanceFactory.get(namespaceA, String.class), is(sameInstance(instance1)));
        assertThat(InstanceFactory.get(namespaceB, String.class), is(sameInstance(instance1)));

        InstanceFactory.remove(defaultNamespace, String.class);

        assertThat(InstanceFactory.get(String.class), is(not(sameInstance(instance1))));
        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(not(sameInstance(instance1))));
        assertThat(InstanceFactory.get(namespaceA, String.class), is(sameInstance(instance1)));
        assertThat(InstanceFactory.get(namespaceB, String.class), is(sameInstance(instance1)));

        InstanceFactory.remove(namespaceA, String.class);

        assertThat(InstanceFactory.get(String.class), is(not(sameInstance(instance1))));
        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(not(sameInstance(instance1))));
        assertThat(InstanceFactory.get(namespaceA, String.class), is(not(sameInstance(instance1))));
        assertThat(InstanceFactory.get(namespaceB, String.class), is(sameInstance(instance1)));

        InstanceFactory.remove(namespaceB, String.class);

        assertThat(InstanceFactory.get(String.class), is(not(sameInstance(instance1))));
        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(not(sameInstance(instance1))));
        assertThat(InstanceFactory.get(namespaceA, String.class), is(not(sameInstance(instance1))));
        assertThat(InstanceFactory.get(namespaceB, String.class), is(not(sameInstance(instance1))));
    }

    @Test
    public void removingInstanceWithParametersAndGettingNewOne() {
        final String instance = "test";
        InstanceFactory.set(namespaceA, instance, String.class, new Class[]{String.class}, "test");
        InstanceFactory.remove(namespaceA, String.class, new Class[]{String.class}, "test");

        final String instance2 = InstanceFactory.get(namespaceA, String.class, new Class[]{String.class}, "test");

        assertThat(instance, is(not(sameInstance(instance2))));
    }

    @Test
    public void clearingInstanceFactoryLeadsToAllInstanceBeingCreatedFreshlyDefaultNamespace() {
        final TestInstance integerInstance1 = InstanceFactory.get(defaultNamespace, TestInstance.class);
        final String stringInstance1 = InstanceFactory.get(defaultNamespace, String.class);

        InstanceFactory.clear();

        final TestInstance integerInstance2 = InstanceFactory.get(defaultNamespace, TestInstance.class);
        final String stringInstance2 = InstanceFactory.get(defaultNamespace, String.class);

        assertThat(integerInstance1, is(not(sameInstance(integerInstance2))));
        assertThat(stringInstance1, is(not(sameInstance(stringInstance2))));
    }

    @Test
    public void clearingInstanceFactoryLeadsToAllInstanceBeingCreatedFreshly() {
        final TestInstance integerInstance1 = InstanceFactory.get(namespaceA, TestInstance.class);
        final String stringInstance1 = InstanceFactory.get(namespaceA, String.class);

        InstanceFactory.clear(namespaceA);

        final TestInstance integerInstance2 = InstanceFactory.get(namespaceA, TestInstance.class);
        final String stringInstance2 = InstanceFactory.get(namespaceA, String.class);

        assertThat(integerInstance1, is(not(sameInstance(integerInstance2))));
        assertThat(stringInstance1, is(not(sameInstance(stringInstance2))));
    }

    @Test
    public void clearingInstanceFactorySpecificNamespaceLeadsToAllInstanceBeingCreatedFreshly() {
        final TestInstance integerInstance1 = InstanceFactory.get(namespaceA, TestInstance.class);
        final String stringInstance1 = InstanceFactory.get(namespaceA, String.class);

        InstanceFactory.clear(namespaceB);

        final TestInstance integerInstance2 = InstanceFactory.get(namespaceA, TestInstance.class);
        final String stringInstance2 = InstanceFactory.get(namespaceA, String.class);

        assertThat(integerInstance1, is(sameInstance(integerInstance2)));
        assertThat(stringInstance1, is(sameInstance(stringInstance2)));
    }

    @Test
    public void clearingAllInstanceFactoryLeadsToAllInstanceBeingCreatedFreshly() {
        final String instance1 = "test1";
        final String instance2 = "test2";
        final String instance3 = "test3";

        InstanceFactory.set(defaultNamespace, instance1);
        InstanceFactory.set(namespaceA, instance2);
        InstanceFactory.set(namespaceB, instance3);

        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(sameInstance(instance1)));
        assertThat(InstanceFactory.get(namespaceA, String.class), is(sameInstance(instance2)));
        assertThat(InstanceFactory.get(namespaceB, String.class), is(sameInstance(instance3)));

        InstanceFactory.clearAll();

        assertThat(InstanceFactory.get(defaultNamespace, String.class), is(not(sameInstance(instance1))));
        assertThat(InstanceFactory.get(namespaceA, String.class), is(not(sameInstance(instance2))));
        assertThat(InstanceFactory.get(namespaceB, String.class), is(not(sameInstance(instance3))));
    }

}

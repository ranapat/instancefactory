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

public class DefaultNamespaceTest {

    @After
    public void after() {
        InstanceFactory.clear();
    }

    @Test
    public void gettingNotPresentInstanceProducesNewInstance() {
        final String instance = InstanceFactory.get(String.class);

        assertThat(instance, is(not(nullValue())));
    }

    @Test
    public void gettingTwoTimesSameClassInstanceReturnsSameInstance() {
        final String instance1 = InstanceFactory.get(String.class);
        final String instance2 = InstanceFactory.get(String.class);

        assertThat(instance1, is(sameInstance(instance2)));
    }

    @Test
    public void gettingInstancesWithParametersCaches() {
        final String instance1 = InstanceFactory.get(String.class, new Class[]{String.class}, "something");
        final String instance2 = InstanceFactory.get(String.class, new Class[]{String.class}, "something");

        assertThat(instance1, is(sameInstance(instance2)));
    }

    @Test
    public void gettingInstancesWithDifferentParametersDiffers() {
        final String instance1 = InstanceFactory.get(String.class, new Class[]{String.class}, "something1");
        final String instance2 = InstanceFactory.get(String.class, new Class[]{String.class}, "something2");

        assertThat(instance1, is(not(sameInstance(instance2))));
    }

    @Test
    public void instanceOfClassWithoutDefaultConstructorLeadsToNullValue() {
        final TestClassWithPrivateConstructor testClassWithPrivateConstructor = InstanceFactory.get(TestClassWithPrivateConstructor.class);

        assertThat(testClassWithPrivateConstructor, is(nullValue()));
    }

    @Test
    public void instanceOfAbstractClassGetsNull() {
        final TestAbstractClass testAbstractClass = InstanceFactory.get(TestAbstractClass.class);

        assertThat(testAbstractClass, is(nullValue()));
    }

    @Test
    public void returnNullInWrongInput() {
        assertThat(InstanceFactory.get(String.class, new Class[]{String.class}), is(equalTo(null)));
    }

    @Test
    public void setInstanceWithoutClass() {
        final String instance = "test";
        InstanceFactory.set(instance);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance)));
    }

    @Test
    public void setInstanceWithoutClassOverwritesIfSame() {
        final String instance1 = "test1";
        final String instance2 = "test2";
        InstanceFactory.set(instance1);
        InstanceFactory.set(instance2);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance2)));
    }

    @Test
    public void setInstanceForClass() {
        final String instance = "test";
        InstanceFactory.set(instance, String.class);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance)));
    }

    @Test
    public void setInstanceForClassOverwritesIfSame() {
        final String instance1 = "test1";
        final String instance2 = "test1";
        InstanceFactory.set(instance1, String.class);
        InstanceFactory.set(instance2, String.class);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance2)));
    }

    @Test
    public void setInstanceForClassWithParameters() {
        final String instance = "test";
        InstanceFactory.set(instance, String.class, new Class[]{String.class}, "test");

        assertThat(InstanceFactory.get(String.class, new Class[]{String.class}, "test"), is(sameInstance(instance)));
    }

    @Test
    public void removingInstanceThatDoesNotExist() {
        InstanceFactory.remove(String.class);
    }

    @Test
    public void removingAllSetInstance() {
        final String instance1 = "test";
        final Integer instance2 = 2;

        InstanceFactory.set(instance1);
        InstanceFactory.set(instance2);

        InstanceFactory.remove(String.class);
        InstanceFactory.remove(Integer.class);
    }

    @Test
    public void removingInstanceAndGettingNewOneWorks() {
        final String instance = "test";
        InstanceFactory.set(instance, String.class);
        InstanceFactory.remove(String.class);

        final String instance2 = InstanceFactory.get(String.class);

        assertThat(instance, is(not(sameInstance(instance2))));
    }

    @Test
    public void removingInstanceWithParametersAndGettingNewOneWorks() {
        final String instance = "test";
        InstanceFactory.set(instance, String.class, new Class[]{String.class}, "test");
        InstanceFactory.remove(String.class, new Class[]{String.class}, "test");

        final String instance2 = InstanceFactory.get(String.class, new Class[]{String.class}, "test");

        assertThat(instance, is(not(sameInstance(instance2))));
    }

    @Test
    public void clearingInstanceFactoryLeadsToAllInstanceBeingCreatedFreshly() {
        final TestInstance integerInstance1 = InstanceFactory.get(TestInstance.class);
        final String stringInstance1 = InstanceFactory.get(String.class);

        InstanceFactory.clear();

        final TestInstance integerInstance2 = InstanceFactory.get(TestInstance.class);
        final String stringInstance2 = InstanceFactory.get(String.class);

        assertThat(integerInstance1, is(not(sameInstance(integerInstance2))));
        assertThat(stringInstance1, is(not(sameInstance(stringInstance2))));
    }

}

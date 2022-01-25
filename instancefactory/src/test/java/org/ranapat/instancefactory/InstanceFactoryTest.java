package org.ranapat.instancefactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

import org.junit.After;
import org.junit.Test;
import org.ranapat.instancefactory.tools.ClassWithFields1;
import org.ranapat.instancefactory.tools.ClassWithFields2;
import org.ranapat.instancefactory.tools.ExtraTestInstance;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV1;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV2;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV3;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV4;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV5;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV6;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV7;
import org.ranapat.instancefactory.tools.StaticallyMarkedA;
import org.ranapat.instancefactory.tools.StaticallyMarkedB;
import org.ranapat.instancefactory.tools.TestAbstractClass;
import org.ranapat.instancefactory.tools.TestClassWithPrivateConstructor;
import org.ranapat.instancefactory.tools.TestInstance;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class InstanceFactoryTest {

    @After
    public void after() {
        InstanceFactory.clear();
    }

    @Test
    public void initializeDynamicallyInitialisableFieldsExplicit() {
        final InstanceToDynamicallyInitiliseV1 instance1 = new InstanceToDynamicallyInitiliseV1();
        final InstanceToDynamicallyInitiliseV2 instance2 = new InstanceToDynamicallyInitiliseV2();
        final InstanceToDynamicallyInitiliseV3 instance3 = new InstanceToDynamicallyInitiliseV3();

        assertThat(instance1.getValue1(), is(equalTo(null)));
        assertThat(instance2.getValue1(), is(equalTo(null)));
        assertThat(instance3.getValue1(), is(equalTo(null)));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance2.getValue2(), is(equalTo(0)));
        assertThat(instance3.getValue2(), is(equalTo(0)));

        InstanceFactory.inject(instance1);
        InstanceFactory.inject(instance2);
        InstanceFactory.inject(instance3);

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
    public void initializeDynamicallyInitialisableFieldsImplicit() {
        final InstanceToDynamicallyInitiliseV1 instance1 = InstanceFactory.get(InstanceToDynamicallyInitiliseV1.class);
        final InstanceToDynamicallyInitiliseV2 instance2 = InstanceFactory.get(InstanceToDynamicallyInitiliseV2.class);
        final InstanceToDynamicallyInitiliseV3 instance3 = InstanceFactory.get(InstanceToDynamicallyInitiliseV3.class);

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
    public void injectShallReturn() {
        final InstanceToDynamicallyInitiliseV1 instance1 = new InstanceToDynamicallyInitiliseV1();
        final InstanceToDynamicallyInitiliseV2 instance2 = new InstanceToDynamicallyInitiliseV2();
        final InstanceToDynamicallyInitiliseV3 instance3 = new InstanceToDynamicallyInitiliseV3();

        assertThat(instance1.getValue1(), is(equalTo(null)));
        assertThat(instance2.getValue1(), is(equalTo(null)));
        assertThat(instance3.getValue1(), is(equalTo(null)));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance2.getValue2(), is(equalTo(0)));
        assertThat(instance3.getValue2(), is(equalTo(0)));

        final InstanceToDynamicallyInitiliseV1 instance11 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseV1());
        final InstanceToDynamicallyInitiliseV2 instance22 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseV2());
        final InstanceToDynamicallyInitiliseV3 instance33 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseV3());

        assertThat(instance11.getValue1(), is(not(equalTo(null))));
        assertThat(instance22.getValue1(), is(equalTo(null)));
        assertThat(instance33.getValue1(), is(not(equalTo(null))));
        assertThat(instance11.getValue2(), is(equalTo(0)));
        assertThat(instance22.getValue2(), is(equalTo(0)));
        assertThat(instance33.getValue2(), is(equalTo(0)));
        assertThat(instance11.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance33.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance11.getValue1(), is(equalTo(instance33.getValue1())));
    }

    @Test
    public void injectWithPredefinedType() {
        final InstanceToDynamicallyInitiliseV5 instance5 = new InstanceToDynamicallyInitiliseV5();

        assertThat(instance5.getValue1(), is(equalTo(null)));
        assertThat(instance5.getValue2(), is(equalTo(0)));

        final InstanceToDynamicallyInitiliseV3 instance33 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseV3());
        final InstanceToDynamicallyInitiliseV5 instance55 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseV5());

        assertThat(instance55.getValue1(), is(not(equalTo(null))));
        assertThat(instance55.getValue2(), is(equalTo(0)));
        assertThat(instance55.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance55.getValue1() instanceof ExtraTestInstance, is(equalTo(true)));
        assertThat(instance55.getValue1(), is(not(equalTo(instance33.getValue1()))));
        assertThat((ExtraTestInstance) instance55.getValue1(), is(equalTo(InstanceFactory.get(ExtraTestInstance.class))));
    }

    @Test
    public void injectWeakReference() {
        final InstanceToDynamicallyInitiliseV4 instance4 = new InstanceToDynamicallyInitiliseV4();

        assertThat(instance4.getValue1(), is(equalTo(null)));
        assertThat(instance4.getValue2(), is(equalTo(0)));

        final InstanceToDynamicallyInitiliseV3 instance33 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseV3());
        final InstanceToDynamicallyInitiliseV4 instance44 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseV4());

        assertThat(instance44.getValue1(), is(not(equalTo(null))));
        assertThat(instance44.getValue2(), is(equalTo(0)));
        assertThat(instance44.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance44.getValue1(), is(equalTo(instance33.getValue1())));
    }

    @Test
    public void shouldInjectTwiceCase1() {
        final AtomicReference<Integer> injects = new AtomicReference<>(0);

        InstanceFactory.setDebugFeedback(new DebugFeedback() {
            private Map<String, Object> map;

            @Override
            public void attachMap(final Map<String, Object> map) {
                this.map = map;
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
                if (instance instanceof InstanceToDynamicallyInitiliseV1) {
                    injects.set(injects.get() + 1);
                }
            }
        });

        final InstanceToDynamicallyInitiliseV1 instance1 = new InstanceToDynamicallyInitiliseV1();

        assertThat(instance1.getValue1(), is(equalTo(null)));
        assertThat(instance1.getValue2(), is(equalTo(0)));

        InstanceFactory.inject(instance1);
        InstanceFactory.inject(instance1);

        assertThat(instance1.getValue1(), is(not(equalTo(null))));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance1.getValue1() instanceof TestInstance, is(equalTo(true)));

        assertThat(injects.get(), is(equalTo(2)));

        InstanceFactory.resetDebugFeedback();
    }

    @Test
    public void shouldInjectTwiceCase2() {
        final AtomicReference<Integer> injects = new AtomicReference<>(0);

        InstanceFactory.setDebugFeedback(new DebugFeedback() {
            private Map<String, Object> map;

            @Override
            public void attachMap(final Map<String, Object> map) {
                this.map = map;
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
                if (instance instanceof InstanceToDynamicallyInitiliseV6) {
                    injects.set(injects.get() + 1);
                }
            }
        });

        final InstanceToDynamicallyInitiliseV6 instance6 = InstanceFactory.get(InstanceToDynamicallyInitiliseV6.class);

        assertThat(instance6.getValue1(), is(not(equalTo(null))));
        assertThat(instance6.getValue2(), is(equalTo(0)));

        assertThat(instance6.getValue1(), is(not(equalTo(null))));
        assertThat(instance6.getValue2(), is(equalTo(0)));
        assertThat(instance6.getValue1() instanceof TestInstance, is(equalTo(true)));

        assertThat(injects.get(), is(equalTo(2)));

        InstanceFactory.resetDebugFeedback();
    }

    @Test
    public void shouldInjectOnlyOnce() {
        final AtomicReference<Integer> injects = new AtomicReference<>(0);

        InstanceFactory.setDebugFeedback(new DebugFeedback() {
            private Map<String, Object> map;

            @Override
            public void attachMap(final Map<String, Object> map) {
                this.map = map;
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
                if (instance instanceof InstanceToDynamicallyInitiliseV7) {
                    injects.set(injects.get() + 1);
                }
            }
        });

        final InstanceToDynamicallyInitiliseV7 instance7 = InstanceFactory.get(InstanceToDynamicallyInitiliseV7.class);

        assertThat(instance7.getValue1(), is(not(equalTo(null))));
        assertThat(instance7.getValue2(), is(equalTo(0)));

        assertThat(instance7.getValue1(), is(not(equalTo(null))));
        assertThat(instance7.getValue2(), is(equalTo(0)));
        assertThat(instance7.getValue1() instanceof TestInstance, is(equalTo(true)));

        assertThat(injects.get(), is(equalTo(1)));

        InstanceFactory.resetDebugFeedback();
    }

    @Test
    public void gettingNotPresentInstanceShouldProduceNewInstance() {
        final String instance = InstanceFactory.get(String.class);

        assertThat(instance, is(not(nullValue())));
    }

    @Test
    public void gettingTwoTimesSameClassInstanceShouldReturnSameInstance() {
        final String instance1 = InstanceFactory.get(String.class);
        final String instance2 = InstanceFactory.get(String.class);

        assertThat(instance1, is(sameInstance(instance2)));
    }

    @Test
    public void gettingInstancesWithParametersShallCache() {
        final String instance1 = InstanceFactory.get(String.class, new Class[]{String.class}, "something");
        final String instance2 = InstanceFactory.get(String.class, new Class[]{String.class}, "something");

        assertThat(instance1, is(sameInstance(instance2)));
    }

    @Test
    public void gettingInstancesWithDifferentParametersShallDiffer() {
        final String instance1 = InstanceFactory.get(String.class, new Class[]{String.class}, "something1");
        final String instance2 = InstanceFactory.get(String.class, new Class[]{String.class}, "something2");

        assertThat(instance1, is(not(sameInstance(instance2))));
    }

    @Test
    public void setInstanceWithoutClassShouldWork() {
        final String instance = "test";
        InstanceFactory.set(instance);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance)));
    }

    @Test
    public void setInstanceForClassShouldWork() {
        final String instance = "test";
        InstanceFactory.set(instance, String.class);

        assertThat(InstanceFactory.get(String.class), is(sameInstance(instance)));
    }

    @Test
    public void setInstanceForClassWithParametersShouldWork() {
        final String instance = "test";
        InstanceFactory.set(instance, String.class, new Class[]{String.class}, "test");

        assertThat(InstanceFactory.get(String.class, new Class[]{String.class}, "test"), is(sameInstance(instance)));
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
    public void clearingInstanceFactoryShouldLeadToAllInstanceBeingCreatedFreshly() {
        final TestInstance integerInstance1 = InstanceFactory.get(TestInstance.class);
        final String stringInstance1 = InstanceFactory.get(String.class);

        InstanceFactory.clear();

        final TestInstance integerInstance2 = InstanceFactory.get(TestInstance.class);
        final String stringInstance2 = InstanceFactory.get(String.class);

        assertThat(integerInstance1, is(not(sameInstance(integerInstance2))));
        assertThat(stringInstance1, is(not(sameInstance(stringInstance2))));
    }

    @Test
    public void instanceOfClassWithoutDefaultConstructorShouldLeadToNullValue() {
        final TestClassWithPrivateConstructor testClassWithPrivateConstructor = InstanceFactory.get(TestClassWithPrivateConstructor.class);

        assertThat(testClassWithPrivateConstructor, is(nullValue()));
    }

    @Test
    public void instanceOfAbstractClassShouldBeNull() {
        final TestAbstractClass testAbstractClass = InstanceFactory.get(TestAbstractClass.class);

        assertThat(testAbstractClass, is(nullValue()));
    }

    @Test
    public void shouldReturnNullInWrongInput() {
        assertThat(InstanceFactory.get(String.class, new Class[]{String.class}), is(equalTo(null)));
    }

    @Test
    public void shouldNotBeCreatedDirectly() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Constructor<InstanceFactory> constructor = InstanceFactory.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers()), is(equalTo(true)));

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void shallWorkWithStaticallyMarkedWithoutParameters() {
        final StaticallyMarkedA staticallyMarked1 = InstanceFactory.get(StaticallyMarkedA.class);
        assertThat(staticallyMarked1, is(not(nullValue())));

        final StaticallyMarkedA staticallyMarked2 = InstanceFactory.get(StaticallyMarkedA.class);
        assertThat(staticallyMarked2, is(equalTo(staticallyMarked2)));
    }

    @Test
    public void shallWorkWithStaticallyMarkedWithParameters() {
        final StaticallyMarkedB staticallyMarked1 = InstanceFactory.get(StaticallyMarkedB.class, new Class[]{String.class}, "something");
        assertThat(staticallyMarked1, is(not(nullValue())));

        final StaticallyMarkedB staticallyMarked2 = InstanceFactory.get(StaticallyMarkedB.class, new Class[]{String.class}, "something");
        assertThat(staticallyMarked2, is(equalTo(staticallyMarked2)));

        final StaticallyMarkedB staticallyMarked3 = InstanceFactory.get(StaticallyMarkedB.class, new Class[]{String.class}, "somethingElse");
        assertThat(staticallyMarked2, is(not(equalTo(staticallyMarked3))));
    }

    @Test
    public void shallGetAllFields() {
        final List<Field> fields1 = InstanceFactory.getDeclaredFields(ClassWithFields1.class);
        locate(fields1, "java.lang.Integer");
        locate(fields1, "java.lang.String");

        final List<Field> fields2 = InstanceFactory.getDeclaredFields(ClassWithFields2.class);
        locate(fields2, "java.lang.Integer");
        locate(fields2, "java.lang.String");
        locate(fields2, "java.lang.Float");
    }

    private void locate(final List<Field> fields, final String name) {
        boolean located = false;
        for (final Field field : fields) {
            if (field.getType().getName().equals(name)) {
                located = true;
                break;
            }
        }
        assertThat(located, is(equalTo(true)));
    }

}

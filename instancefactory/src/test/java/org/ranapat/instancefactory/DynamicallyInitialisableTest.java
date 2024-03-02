package org.ranapat.instancefactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

import org.junit.After;
import org.junit.Test;
import org.ranapat.instancefactory.tools.ExtraTestInstance;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV1;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV2;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV3;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV4;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV5;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV6;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV7;
import org.ranapat.instancefactory.tools.TestInstance;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class DynamicallyInitialisableTest {

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
    public void injectReturns() {
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
            private Map<Namespace, Map<String, Object>> namespaces;

            @Override
            public void attachNamespaces(final Map<Namespace, Map<String, Object>> namespaces) {
                this.namespaces = namespaces;
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
            public void handleClear(final Namespace namespace) {
                //
            }

            @Override
            public void handleClearAll() {
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
            private Map<Namespace, Map<String, Object>> namespaces;

            @Override
            public void attachNamespaces(final Map<Namespace, Map<String, Object>> namespaces) {
                this.namespaces = namespaces;
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
            public void handleClear(final Namespace namespace) {
                //
            }

            @Override
            public void handleClearAll() {
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
            private Map<Namespace, Map<String, Object>> namespaces;

            @Override
            public void attachNamespaces(final Map<Namespace, Map<String, Object>> namespaces) {
                this.namespaces = namespaces;
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
            public void handleClear(final Namespace namespace) {
                //
            }

            @Override
            public void handleClearAll() {
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

}

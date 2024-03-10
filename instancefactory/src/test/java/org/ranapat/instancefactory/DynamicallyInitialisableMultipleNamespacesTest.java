package org.ranapat.instancefactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ranapat.instancefactory.tools.ExtraTestInstance;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseMultipleNamespacesV1;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseMultipleNamespacesV2;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseMultipleNamespacesV3;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseMultipleNamespacesV4;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseMultipleNamespacesV5;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseMultipleNamespacesV6;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseMultipleNamespacesV7;
import org.ranapat.instancefactory.tools.TestInstance;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class DynamicallyInitialisableMultipleNamespacesTest {
    private static final Namespace namespaceA = new Namespace() {};

    @Before
    public void before() {
        Registry.set("namespaceA", namespaceA);
    }

    @After
    public void after() {
        InstanceFactory.clearAll();
        Registry.unset("namespaceA");
    }

    @Test
    public void initializeDynamicallyInitialisableFieldsExplicit() {
        final InstanceToDynamicallyInitiliseMultipleNamespacesV1 instance1 = new InstanceToDynamicallyInitiliseMultipleNamespacesV1();
        final InstanceToDynamicallyInitiliseMultipleNamespacesV2 instance2 = new InstanceToDynamicallyInitiliseMultipleNamespacesV2();
        final InstanceToDynamicallyInitiliseMultipleNamespacesV3 instance3 = new InstanceToDynamicallyInitiliseMultipleNamespacesV3();

        assertThat(instance1.getValue1(), is(equalTo(null)));
        assertThat(instance2.getValue1(), is(equalTo(null)));
        assertThat(instance3.getValue1(), is(equalTo(null)));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance2.getValue2(), is(equalTo(0)));
        assertThat(instance3.getValue2(), is(equalTo(0)));
        assertThat(instance1.getValue3(), is(equalTo(null)));
        assertThat(instance2.getValue3(), is(equalTo(0)));
        assertThat(instance3.getValue3(), is(equalTo(null)));

        InstanceFactory.inject(instance1);
        InstanceFactory.inject(instance2);
        InstanceFactory.inject(instance3);

        assertThat(instance1.getValue1(), is(not(equalTo(null))));
        assertThat(instance2.getValue1(), is(equalTo(null)));
        assertThat(instance3.getValue1(), is(not(equalTo(null))));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance2.getValue2(), is(equalTo(0)));
        assertThat(instance3.getValue2(), is(equalTo(0)));
        assertThat(instance1.getValue3(), is(not(equalTo(null))));
        assertThat(instance2.getValue3(), is(equalTo(0)));
        assertThat(instance3.getValue3(), is(not(equalTo(null))));
        assertThat(instance1.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance1.getValue3() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance3.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance3.getValue3() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance1.getValue1(), is(equalTo(instance3.getValue1())));
        assertThat(instance1.getValue3(), is(equalTo(instance3.getValue3())));
        assertThat(instance1.getValue1(), is(not(equalTo(instance1.getValue3()))));
        assertThat(instance3.getValue1(), is(not(equalTo(instance3.getValue3()))));
    }

    @Test
    public void initializeDynamicallyInitialisableFieldsImplicit() {
        final InstanceToDynamicallyInitiliseMultipleNamespacesV1 instance1 = InstanceFactory.get(InstanceToDynamicallyInitiliseMultipleNamespacesV1.class);
        final InstanceToDynamicallyInitiliseMultipleNamespacesV2 instance2 = InstanceFactory.get(InstanceToDynamicallyInitiliseMultipleNamespacesV2.class);
        final InstanceToDynamicallyInitiliseMultipleNamespacesV3 instance3 = InstanceFactory.get(InstanceToDynamicallyInitiliseMultipleNamespacesV3.class);

        assertThat(instance1.getValue1(), is(not(equalTo(null))));
        assertThat(instance2.getValue1(), is(equalTo(null)));
        assertThat(instance3.getValue1(), is(not(equalTo(null))));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance2.getValue2(), is(equalTo(0)));
        assertThat(instance3.getValue2(), is(equalTo(0)));
        assertThat(instance1.getValue3(), is(not(equalTo(null))));
        assertThat(instance2.getValue3(), is(equalTo(0)));
        assertThat(instance3.getValue3(), is(not(equalTo(null))));
        assertThat(instance1.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance1.getValue3() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance3.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance3.getValue3() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance1.getValue1(), is(equalTo(instance3.getValue1())));
        assertThat(instance1.getValue3(), is(equalTo(instance3.getValue3())));
        assertThat(instance1.getValue1(), is(not(equalTo(instance1.getValue3()))));
        assertThat(instance3.getValue1(), is(not(equalTo(instance3.getValue3()))));
    }

    @Test
    public void injectReturns() {
        final InstanceToDynamicallyInitiliseMultipleNamespacesV1 instance1 = new InstanceToDynamicallyInitiliseMultipleNamespacesV1();
        final InstanceToDynamicallyInitiliseMultipleNamespacesV2 instance2 = new InstanceToDynamicallyInitiliseMultipleNamespacesV2();
        final InstanceToDynamicallyInitiliseMultipleNamespacesV3 instance3 = new InstanceToDynamicallyInitiliseMultipleNamespacesV3();

        assertThat(instance1.getValue1(), is(equalTo(null)));
        assertThat(instance2.getValue1(), is(equalTo(null)));
        assertThat(instance3.getValue1(), is(equalTo(null)));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance2.getValue2(), is(equalTo(0)));
        assertThat(instance3.getValue2(), is(equalTo(0)));
        assertThat(instance1.getValue3(), is(equalTo(null)));
        assertThat(instance2.getValue3(), is(equalTo(0)));
        assertThat(instance3.getValue3(), is(equalTo(null)));

        final InstanceToDynamicallyInitiliseMultipleNamespacesV1 instance11 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseMultipleNamespacesV1());
        final InstanceToDynamicallyInitiliseMultipleNamespacesV2 instance22 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseMultipleNamespacesV2());
        final InstanceToDynamicallyInitiliseMultipleNamespacesV3 instance33 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseMultipleNamespacesV3());

        assertThat(instance11.getValue1(), is(not(equalTo(null))));
        assertThat(instance22.getValue1(), is(equalTo(null)));
        assertThat(instance33.getValue1(), is(not(equalTo(null))));
        assertThat(instance11.getValue2(), is(equalTo(0)));
        assertThat(instance22.getValue2(), is(equalTo(0)));
        assertThat(instance33.getValue2(), is(equalTo(0)));
        assertThat(instance11.getValue3(), is(not(equalTo(null))));
        assertThat(instance22.getValue3(), is(equalTo(0)));
        assertThat(instance33.getValue3(), is(not(equalTo(null))));
        assertThat(instance11.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance11.getValue3() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance33.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance33.getValue3() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance11.getValue1(), is(equalTo(instance33.getValue1())));
        assertThat(instance11.getValue3(), is(equalTo(instance33.getValue3())));
        assertThat(instance11.getValue1(), is(not(equalTo(instance11.getValue3()))));
        assertThat(instance33.getValue1(), is(not(equalTo(instance33.getValue3()))));
    }

    @Test
    public void injectWithPredefinedType() {
        final InstanceToDynamicallyInitiliseMultipleNamespacesV5 instance5 = new InstanceToDynamicallyInitiliseMultipleNamespacesV5();

        assertThat(instance5.getValue1(), is(equalTo(null)));
        assertThat(instance5.getValue2(), is(equalTo(0)));
        assertThat(instance5.getValue3(), is(equalTo(null)));

        final InstanceToDynamicallyInitiliseMultipleNamespacesV3 instance33 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseMultipleNamespacesV3());
        final InstanceToDynamicallyInitiliseMultipleNamespacesV5 instance55 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseMultipleNamespacesV5());

        assertThat(instance55.getValue1(), is(not(equalTo(null))));
        assertThat(instance55.getValue2(), is(equalTo(0)));
        assertThat(instance55.getValue3(), is(not(equalTo(null))));
        assertThat(instance55.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance55.getValue1() instanceof ExtraTestInstance, is(equalTo(true)));
        assertThat(instance55.getValue3() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance55.getValue3() instanceof ExtraTestInstance, is(equalTo(true)));
        assertThat(instance55.getValue1(), is(not(equalTo(instance33.getValue1()))));
        assertThat(instance55.getValue3(), is(not(equalTo(instance33.getValue3()))));
        assertThat((ExtraTestInstance) instance55.getValue1(), is(equalTo(InstanceFactory.get(ExtraTestInstance.class))));
        assertThat((ExtraTestInstance) instance55.getValue3(), is(equalTo(InstanceFactory.get(namespaceA, ExtraTestInstance.class))));
    }

    @Test
    public void injectWeakReference() {
        final InstanceToDynamicallyInitiliseMultipleNamespacesV4 instance4 = new InstanceToDynamicallyInitiliseMultipleNamespacesV4();

        assertThat(instance4.getValue1(), is(equalTo(null)));
        assertThat(instance4.getValue2(), is(equalTo(0)));
        assertThat(instance4.getValue3(), is(equalTo(null)));

        final InstanceToDynamicallyInitiliseMultipleNamespacesV3 instance33 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseMultipleNamespacesV3());
        final InstanceToDynamicallyInitiliseMultipleNamespacesV4 instance44 = InstanceFactory.inject(new InstanceToDynamicallyInitiliseMultipleNamespacesV4());

        assertThat(instance44.getValue1(), is(not(equalTo(null))));
        assertThat(instance44.getValue2(), is(equalTo(0)));
        assertThat(instance44.getValue3(), is(not(equalTo(null))));
        assertThat(instance44.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance44.getValue3() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance44.getValue1(), is(equalTo(instance33.getValue1())));
        assertThat(instance44.getValue3(), is(equalTo(instance33.getValue3())));
        assertThat(instance44.getValue1(), is(not(equalTo(instance33.getValue3()))));
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
                if (instance instanceof InstanceToDynamicallyInitiliseMultipleNamespacesV1) {
                    injects.set(injects.get() + 1);
                }
            }
        });

        final InstanceToDynamicallyInitiliseMultipleNamespacesV1 instance1 = new InstanceToDynamicallyInitiliseMultipleNamespacesV1();

        assertThat(instance1.getValue1(), is(equalTo(null)));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance1.getValue3(), is(equalTo(null)));

        InstanceFactory.inject(instance1);
        InstanceFactory.inject(instance1);

        assertThat(instance1.getValue1(), is(not(equalTo(null))));
        assertThat(instance1.getValue2(), is(equalTo(0)));
        assertThat(instance1.getValue3(), is(not(equalTo(null))));
        assertThat(instance1.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance1.getValue3() instanceof TestInstance, is(equalTo(true)));

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
                if (instance instanceof InstanceToDynamicallyInitiliseMultipleNamespacesV6) {
                    injects.set(injects.get() + 1);
                }
            }
        });

        final InstanceToDynamicallyInitiliseMultipleNamespacesV6 instance6 = InstanceFactory.get(InstanceToDynamicallyInitiliseMultipleNamespacesV6.class);

        assertThat(instance6.getValue1(), is(not(equalTo(null))));
        assertThat(instance6.getValue2(), is(equalTo(0)));
        assertThat(instance6.getValue3(), is(not(equalTo(null))));

        assertThat(instance6.getValue1(), is(not(equalTo(null))));
        assertThat(instance6.getValue2(), is(equalTo(0)));
        assertThat(instance6.getValue3(), is(not(equalTo(null))));
        assertThat(instance6.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance6.getValue3() instanceof TestInstance, is(equalTo(true)));

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
                if (instance instanceof InstanceToDynamicallyInitiliseMultipleNamespacesV7) {
                    injects.set(injects.get() + 1);
                }
            }
        });

        final InstanceToDynamicallyInitiliseMultipleNamespacesV7 instance7 = InstanceFactory.get(InstanceToDynamicallyInitiliseMultipleNamespacesV7.class);

        assertThat(instance7.getValue1(), is(not(equalTo(null))));
        assertThat(instance7.getValue2(), is(equalTo(0)));
        assertThat(instance7.getValue3(), is(not(equalTo(null))));

        assertThat(instance7.getValue1(), is(not(equalTo(null))));
        assertThat(instance7.getValue2(), is(equalTo(0)));
        assertThat(instance7.getValue3(), is(not(equalTo(null))));
        assertThat(instance7.getValue1() instanceof TestInstance, is(equalTo(true)));
        assertThat(instance7.getValue3() instanceof TestInstance, is(equalTo(true)));

        assertThat(injects.get(), is(equalTo(1)));

        InstanceFactory.resetDebugFeedback();
    }

}

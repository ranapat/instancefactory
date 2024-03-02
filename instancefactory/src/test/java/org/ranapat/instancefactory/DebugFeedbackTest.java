package org.ranapat.instancefactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ranapat.instancefactory.tools.InstanceToDynamicallyInitiliseV1;
import org.ranapat.instancefactory.tools.TestInstance;

import java.util.Map;

public class DebugFeedbackTest {
    private final DebugFeedback debugFeedback;

    public DebugFeedbackTest() {
        debugFeedback = new DebugFeedback() {
            @Override
            public void attachNamespaces(final Map<Namespace, Map<String, Object>> namespaces) {
                //
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
            public void handleInject(final Object instance) {
                //
            }
        };
    }

    @Before
    public void before() {
        InstanceFactory.setDebugFeedback(debugFeedback);
    }

    @After
    public void after() {
        InstanceFactory.clear();
        InstanceFactory.resetDebugFeedback();
    }

    @Test
    public void shouldHandleAllEvents() {
        final TestInstance testInstance = new TestInstance();
        InstanceFactory.set(testInstance);
        InstanceFactory.get(TestInstance.class);
        InstanceFactory.remove(TestInstance.class);
        InstanceFactory.inject(new InstanceToDynamicallyInitiliseV1());
    }

}

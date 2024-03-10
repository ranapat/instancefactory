package org.ranapat.instancefactory.tools;

import org.ranapat.instancefactory.Inject;

import java.lang.ref.WeakReference;

public class InstanceToDynamicallyInitiliseMultipleNamespacesV4 {
    @Inject(type = TestInstance.class)
    private final WeakReference<TestInstance> value1 = null;
    private int value2;
    @Inject(namespace = "namespaceA", type = TestInstance.class)
    private final WeakReference<TestInstance> value3 = null;

    public TestInstance getValue1() {
        return value1 != null ? value1.get() : null;
    }

    public int getValue2() {
        return value2;
    }

    public TestInstance getValue3() {
        return value3 != null ? value3.get() : null;
    }
}

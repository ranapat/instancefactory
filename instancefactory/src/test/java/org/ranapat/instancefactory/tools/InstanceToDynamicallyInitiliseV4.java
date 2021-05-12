package org.ranapat.instancefactory.tools;

import org.ranapat.instancefactory.Inject;

import java.lang.ref.WeakReference;

public class InstanceToDynamicallyInitiliseV4 {
    @Inject(type = TestInstance.class)
    private final WeakReference<TestInstance> value1 = null;
    private int value2;

    public TestInstance getValue1() {
        return value1 != null ? value1.get() : null;
    }

    public int getValue2() {
        return value2;
    }
}

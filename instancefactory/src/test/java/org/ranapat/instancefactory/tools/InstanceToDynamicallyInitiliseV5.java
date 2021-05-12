package org.ranapat.instancefactory.tools;

import org.ranapat.instancefactory.Inject;

public class InstanceToDynamicallyInitiliseV5 {
    @Inject(type = ExtraTestInstance.class)
    private final TestInstance value1 = null;
    private int value2;

    public TestInstance getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
}

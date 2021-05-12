package org.ranapat.instancefactory.tools;

import org.ranapat.instancefactory.Inject;
import org.ranapat.instancefactory.InstanceFactory;

public class InstanceToDynamicallyInitiliseV6 {
    @Inject
    private TestInstance value1;
    private int value2;

    public InstanceToDynamicallyInitiliseV6() {
        InstanceFactory.inject(this);
    }

    public TestInstance getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }
}

package org.ranapat.instancefactory.tools;

import org.ranapat.instancefactory.Static;

@Static(method = "getInstanceCustom")
public class StaticallyMarkedB {
    public static StaticallyMarkedB getInstanceCustom(final String parameter) {
        return new StaticallyMarkedB(parameter);
    }

    private StaticallyMarkedB(final String parameter) {}
}

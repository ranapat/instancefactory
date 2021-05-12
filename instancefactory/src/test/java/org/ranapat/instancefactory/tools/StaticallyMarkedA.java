package org.ranapat.instancefactory.tools;

import org.ranapat.instancefactory.Static;

@Static
public class StaticallyMarkedA {
    public static StaticallyMarkedA getInstance() {
        return new StaticallyMarkedA();
    }

    private StaticallyMarkedA() {}
}

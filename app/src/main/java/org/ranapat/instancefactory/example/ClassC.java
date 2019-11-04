package org.ranapat.instancefactory.example;

import android.util.Log;

public class ClassC implements Explainable {
    private final double random;
    private final int integer;

    public ClassC(final Integer integer) {
        random = Math.random();
        this.integer = integer;

        Log.d("### DEBUG ###", "new instance of C with " + integer);
    }

    @Override
    public void explain() {
        Log.d("### DEBUG ###", "Instance of Class C " + random + " " + integer);
    }
}

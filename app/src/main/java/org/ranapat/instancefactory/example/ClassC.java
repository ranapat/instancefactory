package org.ranapat.instancefactory.example;

import android.util.Log;

public class ClassC implements Explainable {
    private final double random;
    private final String string;

    public ClassC(final String string) {
        random = Math.random();
        this.string = string;

        Log.d("### DEBUG ###", "new instance of C with " + string);
    }

    @Override
    public void explain() {
        Log.d("### DEBUG ###", "Instance of Class C " + random + " " + string);
    }
}

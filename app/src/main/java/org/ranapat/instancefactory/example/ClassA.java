package org.ranapat.instancefactory.example;

import android.util.Log;

public class ClassA implements Explainable {
    private final double random;

    public ClassA() {
        random = Math.random();

        Log.d("### DEBUG ###", "Class A constructor " + random);
    }

    @Override
    public void explain() {
        Log.d("### DEBUG ###", "Instance of Class A " + random);
    }
}

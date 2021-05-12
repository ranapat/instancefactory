package org.ranapat.instancefactory.example;

import android.util.Log;

import org.ranapat.instancefactory.Static;

@Static
public class ClassB implements Explainable {
    private static ClassB instance;
    public static ClassB getInstance() {
        Log.d("### DEBUG ###", "getInstance Class B (outside)");

        if (instance == null) {
            Log.d("### DEBUG ###", "getInstance Class B (inside)");
            instance = new ClassB();
        }

        return instance;
    }

    private final double random;

    private ClassB() {
        random = Math.random();

        Log.d("### DEBUG ###", "Class B constructor " + random);
    }

    @Override
    public void explain() {
        Log.d("### DEBUG ###", "Instance of Class B " + random);
    }
}

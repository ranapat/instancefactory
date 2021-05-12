package org.ranapat.instancefactory.example;

import android.util.Log;

import org.ranapat.instancefactory.Static;

@Static
public class ClassD implements Explainable {
    private static ClassD instance;
    public static ClassD getInstance(final String passed) {
        Log.d("### DEBUG ###", "getInstance Class D (outside)");

        if (instance == null) {
            Log.d("### DEBUG ###", "getInstance Class D (inside)");
            instance = new ClassD(passed);
        }

        return instance;
    }

    private final double random;
    private final String passed;

    private ClassD(final String passed) {
        random = Math.random();
        this.passed = passed;

        Log.d("### DEBUG ###", "Class D constructor " + random + " " + passed);
    }

    @Override
    public void explain() {
        Log.d("### DEBUG ###", "Instance of Class D " + random + " " + passed);
    }
}

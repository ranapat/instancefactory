package org.ranapat.instancefactory.example;

import android.util.Log;

import org.ranapat.instancefactory.Inject;

public class ClassG implements Explainable {
    private final double random;

    @Inject
    private final ClassA classA = null;

    public ClassG() {
        random = Math.random();

        Log.d("### DEBUG ###", "Class G constructor " + random + " " + classA);
        if (classA != null) {
            Log.d("### DEBUG ###", "Class G constructor explain classA");
            classA.explain();
        } else {
            Log.d("### DEBUG ###", "Class G constructor cannot go here, reflection takes time");
        }
    }

    @Override
    public void explain() {
        Log.d("### DEBUG ###", "Instance of Class G " + random + " " + classA);
        if (classA != null) {
            Log.d("### DEBUG ###", "Instance of Class G explain classA");
            classA.explain();
        }
    }
}

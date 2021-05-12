package org.ranapat.instancefactory.example;

import android.util.Log;

import org.ranapat.instancefactory.Inject;

public class ClassE implements Explainable {
    private final double random;

    @Inject
    private final ClassA classA = null;

    public ClassE() {
        random = Math.random();

        Log.d("### DEBUG ###", "Class E constructor " + random + " " + classA);
        if (classA != null) {
            Log.d("### DEBUG ###", "Class E constructor explain classA");
            classA.explain();
        }
    }

    @Override
    public void explain() {
        Log.d("### DEBUG ###", "Instance of Class E " + random + " " + classA);
        if (classA != null) {
            Log.d("### DEBUG ###", "Instance of Class E explain classA");
            classA.explain();
        }
    }
}

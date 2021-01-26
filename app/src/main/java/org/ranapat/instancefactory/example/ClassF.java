package org.ranapat.instancefactory.example;

import android.util.Log;

import org.ranapat.instancefactory.DynamicallyInitialisable;
import org.ranapat.instancefactory.InstanceFactory;

public class ClassF implements Explainable {
    private final double random;

    @DynamicallyInitialisable
    private final ClassA classA = null;

    public ClassF() {
        // move at the end of the constructor, makes no sense to be here
        InstanceFactory.initialise(this);
        random = Math.random();

        Log.d("### DEBUG ###", "Class F constructor " + random + " " + classA);
        if (classA != null) {
            Log.d("### DEBUG ###", "Class F constructor explain classA");
            classA.explain();
        } else {
            Log.d("### DEBUG ###", "Class F constructor cannot go here, reflection takes time");
        }

        // move at the end of the constructor, makes no sense to be on the top
        // InstanceFactory.initialise(this);
    }

    @Override
    public void explain() {
        Log.d("### DEBUG ###", "Instance of Class F " + random + " " + classA);
        if (classA != null) {
            Log.d("### DEBUG ###", "Instance of Class F explain classA");
            classA.explain();
        }
    }
}

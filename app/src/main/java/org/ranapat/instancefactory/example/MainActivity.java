package org.ranapat.instancefactory.example;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.ranapat.instancefactory.InstanceFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ClassA instanceA1 = InstanceFactory.get(ClassA.class);
        instanceA1.explain();

        final ClassA instanceA2 = InstanceFactory.get(ClassA.class);
        instanceA2.explain();

        InstanceFactory.remove(ClassA.class);

        final ClassA instanceA3 = new ClassA();
        InstanceFactory.set(instanceA3, ClassA.class);

        final ClassA instanceA3_1 = InstanceFactory.get(ClassA.class);
        instanceA3_1.explain();
        final ClassA instanceA3_2 = InstanceFactory.get(ClassA.class);
        instanceA3_2.explain();

        final ClassB classB1 = InstanceFactory.get(ClassB.class);
        classB1.explain();

        final ClassB classB2 = InstanceFactory.get(ClassB.class);
        classB2.explain();

        final ClassC classC1 = InstanceFactory.get(ClassC.class, new Class[]{Integer.class}, 12);
        classC1.explain();

        final ClassC classC2 = InstanceFactory.get(ClassC.class, new Class[]{Integer.class}, 12);
        classC2.explain();

        final ClassD classD1 = InstanceFactory.get(ClassD.class, new Class[]{String.class}, "something");
        classD1.explain();

        final ClassD classD2 = InstanceFactory.get(ClassD.class, new Class[]{String.class}, "something");
        classD2.explain();

        final ClassE classE1 = InstanceFactory.get(ClassE.class);
        classE1.explain();

        final ClassE classE2 = InstanceFactory.get(ClassE.class);
        classE2.explain();

        InstanceFactory.inject(classE1);
        InstanceFactory.inject(classE2);

        classE1.explain();
        classE2.explain();

        final ClassF classF1 = InstanceFactory.get(ClassF.class);
        classF1.explain();

        final ClassF classF2 = InstanceFactory.get(ClassF.class);
        classF2.explain();

        final ClassG classG1 = InstanceFactory.get(ClassG.class);
        classG1.explain();

        final ClassG classG2 = InstanceFactory.get(ClassG.class);
        classG2.explain();
    }
}

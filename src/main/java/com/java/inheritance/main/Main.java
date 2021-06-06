package com.java.inheritance.main;

import com.java.inheritance.collections.Orchestra;
import com.java.inheritance.hierarchy.implementation.PercussionInstrument;
import com.java.inheritance.hierarchy.implementation.StringedInstrument;
import com.java.inheritance.hierarchy.implementation.WindInstrument;
import com.java.inheritance.threads.OrchestraThread;

public class Main {

    public static void main(String[] args)  {
        Orchestra orchestra = new Orchestra();
        orchestra.add(new StringedInstrument(
                "Арфа",
                350,
                false,
                false));
        orchestra.add(new WindInstrument(
                "Саксофон",
                450,
                false,
                2));
        orchestra.add(new PercussionInstrument(
                "Ксилофон",
                300,
                false,
                true,
                2));
        OrchestraThread orchestraThread1 = new OrchestraThread(orchestra, 0, 100);
        new Thread(orchestraThread1).start();
        OrchestraThread orchestraThread2 = new OrchestraThread(orchestra, 2, 200);
        new Thread(orchestraThread2).start();
        OrchestraThread orchestraThread3 = new OrchestraThread(orchestra, 1, 1100);
        new Thread(orchestraThread3).start();
        OrchestraThread orchestraThread4 = new OrchestraThread(orchestra, 0, 600);
        new Thread(orchestraThread4).start();
    }
}


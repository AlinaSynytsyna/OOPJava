package com.java.inheritance.main;

import com.java.inheritance.collections.Orchestra;
import com.java.inheritance.collections.OrchestraSerializer;
import com.java.inheritance.exceptions.CarryingException;
import com.java.inheritance.hierarchy.base_class.MusicalInstrument;


import java.io.IOException;


public class Main {

    public static void main(String[] args) throws CarryingException, IOException {
        Orchestra orchestra = OrchestraSerializer.deserialize("Orchestra 04-22 222305");
        for (MusicalInstrument i: orchestra) {
            System.out.println(i);
        }
    }
}

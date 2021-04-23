package com.java.inheritance.main;

import com.java.inheritance.collections.Orchestra;
import com.java.inheritance.collections.OrchestraSerializer;
import com.java.inheritance.hierarchy.base_class.MusicalInstrument;
import com.java.inheritance.hierarchy.implementation.PercussionInstrument;
import com.java.inheritance.hierarchy.implementation.WindInstrument;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Orchestra orchestra = OrchestraSerializer.deserialize("Orchestra 04-23 155449");
        assert orchestra != null;
        System.out.println("Десериализованная коллекция:");
        for (MusicalInstrument i : orchestra) {
            System.out.println(i + "\n");
        }
        orchestra.remove();
        orchestra.remove(3);
        orchestra.add(new WindInstrument(
                "Тромбон",
                "high",
                320,
                false,
                1,
                1
        ), 2);
        orchestra.add(new PercussionInstrument(
                "Треугольник",
                "undefined",
                210,
                false,
                1.5,
                true,
                2
        ));
        System.out.println("\nИзмененная коллекция:");
        for (MusicalInstrument i : orchestra) {
            System.out.println(i + "\n");
        }
        System.out.println("\nУдарные инструменты в коллекции:");
        for (MusicalInstrument i : orchestra.findInstrumentsByType(PercussionInstrument.class)) {
            System.out.println(i + "\n");
        }
        System.out.println("\nСортировка инструментов по имени:");
        orchestra.sortByName();
        for (MusicalInstrument i : orchestra) {
            System.out.println(i + "\n");
        }
        System.out.println("\nСортировка инструментов по цене:");
        orchestra.sortByPrice();
        for (MusicalInstrument i : orchestra) {
            System.out.println(i + "\n");
        }
        OrchestraSerializer.serialize(orchestra);
    }
}

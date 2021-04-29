package com.java.inheritance.main;

import com.java.inheritance.collections.Orchestra;
import com.java.inheritance.collections.OrchestraSerializer;
import com.java.inheritance.hierarchy.base_class.MusicalInstrument;
import com.java.inheritance.hierarchy.implementation.PercussionInstrument;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Orchestra orchestra = OrchestraSerializer.deserialize("Orchestra 04-29 164706");
        assert orchestra != null;
        System.out.println("Десериализованная коллекция:");
        for (MusicalInstrument i : orchestra) {
            System.out.println(i + "\n");
        }
        System.out.printf("Общая стоимость оркестра: %.2f%n", orchestra.getTotalOrchestraPrice());
        System.out.printf("Стоимость ударных инструментов: %.2f%n", orchestra.getInstrumentsPriceByType(PercussionInstrument.class));
        System.out.printf("Этот оркестр состот из элементов одного типа: %b", orchestra.isSingleTypeInstrumentsOrchestra());
    }
}


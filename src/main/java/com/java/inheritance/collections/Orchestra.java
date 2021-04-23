package com.java.inheritance.collections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.java.inheritance.hierarchy.base_class.MusicalInstrument;

import java.util.Arrays;
import java.util.Iterator;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Orchestra implements Iterable<MusicalInstrument> {
    private MusicalInstrument[] instruments;

    public Orchestra() {
        instruments = new MusicalInstrument[0];
    }

    public int getCount() {
        return instruments.length;
    }

    public void add(MusicalInstrument instrument) {
        instruments = Arrays.copyOf(instruments, instruments.length + 1);
        instruments[instruments.length - 1] = instrument;
    }

    public void add(MusicalInstrument instrument, int index) {
        if (index > instruments.length)
            throw new ArrayIndexOutOfBoundsException();
        MusicalInstrument[] temp = Arrays.copyOf(instruments, index + 1);
        temp[index] = instrument;
        int arrayIndex = index;
        for (int i = index; i < instruments.length; i++) {
            temp = Arrays.copyOf(temp, temp.length + 1);
            temp[temp.length - 1] = instruments[arrayIndex];
            arrayIndex++;
        }
        instruments = temp;
    }

    public void remove(int index) {
        if (index > instruments.length - 1)
            throw new ArrayIndexOutOfBoundsException();
        MusicalInstrument[] temp = new MusicalInstrument[instruments.length - 1];
        int s = 0;
        for (int i = 0; i < instruments.length; i++) {
            if (i != index) {
                temp[s] = instruments[i];
                s++;
            }
        }
        instruments = temp;
    }

    public void remove() {
        instruments = Arrays.copyOf(instruments, instruments.length - 1);
    }

    public MusicalInstrument getInstrument(int index) {
        if (index > instruments.length - 1)
            throw new ArrayIndexOutOfBoundsException();
        return instruments[index];
    }

    public void setInstrument(int index, MusicalInstrument instrument) {
        if (index > instruments.length - 1)
            throw new ArrayIndexOutOfBoundsException();
        instruments[index] = instrument;
    }

    public void sortByName() {
        for (int i = 0; i < instruments.length; i++) {
            for (int j = i + 1; j < instruments.length; j++) {
                MusicalInstrument tmp = null;
                if (instruments[i].getName().compareToIgnoreCase(instruments[j].getName()) > 0) {
                    tmp = instruments[i];
                    instruments[i] = instruments[j];
                    instruments[j] = tmp;
                }
            }
        }
    }

    public void sortByPrice() {
        for (int i = 0; i < instruments.length; i++) {
            for (int j = i + 1; j < instruments.length; j++) {
                MusicalInstrument tmp = null;
                if (instruments[i].getPrice() > instruments[j].getPrice()) {
                    tmp = instruments[i];
                    instruments[i] = instruments[j];
                    instruments[j] = tmp;
                }
            }
        }
    }

    public MusicalInstrument[] findInstrumentsByType(Class<? extends MusicalInstrument> classToFind) {
        MusicalInstrument[] result = new MusicalInstrument[0];
        for (MusicalInstrument i : instruments) {
            if (i.getClass() == classToFind) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = i;
            }
        }
        return result;
    }

    public MusicalInstrument[] findInstrumentsByName(String name) {
        MusicalInstrument[] result = new MusicalInstrument[0];
        for (MusicalInstrument i : instruments) {
            if (i.getName().equalsIgnoreCase(name)) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = i;
            }
        }
        return result;
    }

    public MusicalInstrument[] findBrokenInstruments() {
        MusicalInstrument[] result = new MusicalInstrument[0];
        for (MusicalInstrument i : instruments) {
            if (i.getIsBroken()) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = i;
            }
        }
        return result;
    }

    public Iterator<MusicalInstrument> iterator() {
        return Arrays.stream(instruments).iterator();
    }
}

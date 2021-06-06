package com.java.inheritance.collections;


import com.java.inheritance.exceptions.BrokenInstrumentException;
import com.java.inheritance.hierarchy.base_class.MusicalInstrument;
import com.java.inheritance.hierarchy.implementation.PercussionInstrument;
import com.java.inheritance.hierarchy.implementation.StringedInstrument;
import com.java.inheritance.hierarchy.implementation.WindInstrument;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Коллекция музыкальных инструментов.
 *
 * @see MusicalInstrument
 */
public class Orchestra implements Iterable<MusicalInstrument>, Serializable {
    private MusicalInstrument[] instruments;

    public Orchestra() {
        instruments = new MusicalInstrument[0];
    }

    /**
     * Получить количество элементов в коллекции.
     */
    public int getCount() {
        return instruments.length;
    }

    /**
     * Добавить новый музыкальный инструмент в коллекцию.t
     */
    public void add(MusicalInstrument instrument) {
        instruments = Arrays.copyOf(instruments, instruments.length + 1);
        instruments[instruments.length - 1] = instrument;
    }

    /**
     * Добавить новый музыкальный инструмент в коллекцию на определенную позицию.
     *
     * @param index позиция, на которую надо вставить новый элемент
     */
    public void add(MusicalInstrument instrument, int index) {
        if (index > instruments.length || index < 0)
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

    /**
     * Убрать музыкальный инструмент из коллекции на определенной позиции.
     *
     * @param index позиция, с которой надо убрать элемент
     */
    public void remove(int index) {
        if (index > instruments.length - 1 || index < 0)
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

    /**
     * Убрать музыкальный инструмент из коллекции.
     */
    public void remove() {
        instruments = Arrays.copyOf(instruments, instruments.length - 1);
    }

    /**
     * Получить музыкальный инструмент по индексу.
     */
    public MusicalInstrument getInstrument(int index) {
        if (index > instruments.length - 1 || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        return instruments[index];
    }

    /**
     * Установить музыкальный инструмент по индексу.
     */
    public void setInstrument(int index, MusicalInstrument instrument) {
        if (index > instruments.length - 1 || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        instruments[index] = instrument;
    }

    /**
     * Сортировать коллекцию по имени инструментов.
     */
    public void sortByName() {
        for (int i = 0; i < instruments.length; i++) {
            for (int j = i + 1; j < instruments.length; j++) {
                MusicalInstrument tmp;
                if (instruments[i].getName().compareToIgnoreCase(instruments[j].getName()) > 0) {
                    tmp = instruments[i];
                    instruments[i] = instruments[j];
                    instruments[j] = tmp;
                }
            }
        }
    }

    /**
     * Сортировать коллекцию по цене инструментов.
     */
    public void sortByPrice() {
        for (int i = 0; i < instruments.length; i++) {
            for (int j = i + 1; j < instruments.length; j++) {
                MusicalInstrument tmp;
                if (instruments[i].getPrice() > instruments[j].getPrice()) {
                    tmp = instruments[i];
                    instruments[i] = instruments[j];
                    instruments[j] = tmp;
                }
            }
        }
    }

    /**
     * Найти инструменты определенного типа.
     *
     * @param classToFind - тип инструментов, которые нужно найти в коллекции
     */
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

    /**
     * Найти инструменты по имени.
     */
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

    /**
     * Найти сломанные инструменты.
     */
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

    /**
     * Получить стоимость всего оркестра.
     */
    public double getTotalOrchestraPrice() {
        OrchestraStatistics os = () -> {
            double result = 0;
            for (MusicalInstrument i : instruments) {
                result += i.getPrice();
            }
            return result;
        };
        return os.getPrice();
    }

    /**
     * Получить стоимость инструментов по типу.
     */
    public double getInstrumentsPriceByType(Class<? extends MusicalInstrument> clazz) {
        OrchestraStatistics os = () -> {
            double result = 0;
            for (MusicalInstrument i : instruments) {
                if (i.getClass() == clazz)
                    result += i.getPrice();
            }
            return result;
        };
        return os.getPrice();
    }
    /**
     * Проверка, все ли инструменты в оркестре одного типа?
     */
    public boolean isSingleTypeInstrumentsOrchestra() {
        Predicate<MusicalInstrument> allWind = instrument -> instrument.getClass() == WindInstrument.class;
        Predicate<MusicalInstrument> allStringed = instrument -> instrument.getClass() == StringedInstrument.class;
        Predicate<MusicalInstrument> allPercussion = instrument -> instrument.getClass() == PercussionInstrument.class;
        return (Arrays.stream(instruments).allMatch(allWind) ||
                Arrays.stream(instruments).allMatch(allStringed) ||
                Arrays.stream(instruments).allMatch(allPercussion));
    }

    public synchronized void playInstrument(int index) throws BrokenInstrumentException {
        if(!instruments[index].getIsPlaying()) {
            try {
                instruments[index].playSound();
                instruments[index].setIsPlaying(true);
                notifyAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else    System.out.printf("Поток %s: 'Музыкальный инструмент %s уже играет!'\n",
                Thread.currentThread().getName(),
                instruments[index].getName());
    }
    public synchronized void stopPlayInstrument(int index) {
        if(instruments[index].getIsPlaying()) {
            try {
                instruments[index].setIsPlaying(false);
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        else System.out.printf("Инструмент %s не играет!\n", instruments[index].getName());
    }

    public Iterator<MusicalInstrument> iterator() {
        return Arrays.stream(instruments).iterator();
    }
}

interface OrchestraStatistics {
    double getPrice();
}


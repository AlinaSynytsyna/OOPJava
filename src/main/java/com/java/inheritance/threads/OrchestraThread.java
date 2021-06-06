package com.java.inheritance.threads;

import com.java.inheritance.collections.Orchestra;
import com.java.inheritance.exceptions.BrokenInstrumentException;

/**
 * Поток для взаимодейтвия с элементами коллекции.
 */
public class OrchestraThread implements Runnable {
    /**
     * Оркестр, с которым взаимодействует поток.
     */
    private final Orchestra orchestra;
    /**
     * Индекс инструмента, на котором надо играть.
     */
    private final int instrumentIndex;
    /**
     * Продолжительность паузы.
     */
    private final int stopTime;

    public OrchestraThread(Orchestra orchestra, int instrumentIndex, int stopTime) {
        this.orchestra = orchestra;
        this.instrumentIndex = instrumentIndex;
        this.stopTime = stopTime;
    }
    /**
     * Запустить поток, сыграть на музыкальном инструменте.
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " начал работу.");
        try {
            orchestra.playInstrument(instrumentIndex);
        }
        catch (BrokenInstrumentException brokenInstrumentException) {
            brokenInstrumentException.printStackTrace();
        }
        try {
            orchestra.stopPlayInstrument(instrumentIndex);
            Thread.sleep(stopTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName().toString() + " закончил работу.");
    }
}

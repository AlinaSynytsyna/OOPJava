package com.java.inheritance.threads;

import com.java.inheritance.collections.Orchestra;
import com.java.inheritance.exceptions.BrokenInstrumentException;

public class OrchestraThread implements Runnable {
    private final Orchestra orchestra;
    private final int instrumentIndex;
    private final int stopTime;

    public OrchestraThread(Orchestra orchestra, int instrumentIndex, int stopTime) {
        this.orchestra = orchestra;
        this.instrumentIndex = instrumentIndex;
        this.stopTime = stopTime;
    }

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

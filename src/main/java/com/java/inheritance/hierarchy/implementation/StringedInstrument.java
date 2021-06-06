package com.java.inheritance.hierarchy.implementation;

import com.java.inheritance.exceptions.BrokenInstrumentException;
import com.java.inheritance.exceptions.CarryingException;
import com.java.inheritance.hierarchy.base_class.MusicalInstrument;
import com.java.inheritance.hierarchy.base_class.Timbre;
import com.java.inheritance.hierarchy.interfaces.ICarriable;
import com.java.inheritance.hierarchy.interfaces.INeedAdditionalTools;

/**
 * Класс-наследник - струнный инструмент.
 *
 * @see MusicalInstrument
 */
public final class StringedInstrument extends MusicalInstrument implements INeedAdditionalTools, ICarriable {
    /**
     * Нужен ли смычок для игры на струнном инструменте?
     */
    private boolean hasBow;

    /**
     * Конструктор класса.
     */
    public StringedInstrument(String name, String timbre, double price, boolean isBroken, double volume, boolean hasBow) {
        this.name = name;
        this.timbre = Timbre.valueOf(timbre.toUpperCase());
        if (price > 0)
            this.price = price;
        else this.price = 0;
        this.isBroken = isBroken;
        if (volume < 0)
            this.volume = 0;
        else if (volume > 1)
            this.volume = 1;
        else this.volume = volume;
        this.hasBow = hasBow;
        isPlaying = false;
    }

    /**
     * Конструктор класса без параметров.
     */
    public StringedInstrument() {
        super();
        hasBow = false;
    }

    /**
     * Конструктор класса с основными параметрами.
     */
    public StringedInstrument(String name, double price, boolean isBroken, boolean hasBow) {
        this.name = name;
        timbre = Timbre.UNDEFINED;
        this.price = price;
        this.isBroken = isBroken;
        volume = 0;
        this.hasBow = hasBow;
        isPlaying = false;
    }

    /**
     * Возвращает значение поля hasBow.
     */
    public boolean getHasBow() {
        return hasBow;
    }

    /**
     * Устанавливает значение поля hasBow.
     */
    public void setHasBow(boolean value) {
        hasBow = value;
    }

    /**
     * Прикоснуться к струнам инструмента.
     */
    public void touchStrings() {
        String bow = hasBow ? "смычком" : "пальцами";
        System.out.printf("Вы прикоснулись к струнам инструмента %s %s.\n", name, bow);
    }

    /**
     * Выводит информацию о струнном инструменте.
     *
     * @see MusicalInstrument
     */
    @Override
    public String toString() {
        String result = String.format("Название струнного инструмента: %s." +
                        " Тембр инструмента: %s." +
                        " Цена за единицу: %.2f.\n" + useAdditionalTool(),
                name,
                getTimbre(),
                price);
        if (isBroken)
            result += "\nЭтот инструмент сломан и нуждается в починке!";
        return result;
    }

    /**
     * Переопределение метода базового класса.
     * Выводит информацию о звуке, проигрываемом инструментом.
     *
     * @see MusicalInstrument
     */
    @Override
    public void playSound() throws BrokenInstrumentException {
        if (isBroken) {
            throw new BrokenInstrumentException(String.format("Сначала необходимо починить струнный инструмент %s!\n", name));
        } else {
            String bow = hasBow ? "с помощью смычка" : "без помощи смычка";
            System.out.printf("Струнный инструмент %s издает %s звук %s.\n", name, getTimbre(), bow);
            System.out.printf("Громкость издаваемого звука: %.2f.\n", volume);
        }
    }

    /**
     * Переопределение метода базового класса.
     * Починить музыкальный инструмент.
     *
     * @see MusicalInstrument
     */
    @Override
    public void repairInstrument() {
        if (!isBroken)
            System.out.printf("Инструмент %s не нуждается в починке.\n", name);
        else {
            System.out.printf("Инструмент %s восстановлен, его стоимость возросла на 20 процентов.\n", name);
            price += price * 0.2;
            isBroken = false;
        }
    }

    /**
     * Переопределение метода базового класса.
     * Изменить громкость инструмента.
     *
     * @see MusicalInstrument
     */
    @Override
    public void changeVolume(double value) {
        if (value >= 0 && value <= 1)
            volume = value;
        System.out.printf("Установлена громкость для инструмента %s: %.2f.\n", name, volume);
    }

    /**
     * Переопределение метода hashCode.
     *
     * @see MusicalInstrument
     */
    @Override
    public int hashCode() {
        int hash = super.hashCode();
        if (hasBow) hash++;
        else hash += 2;
        return 7 * hash;
    }

    /**
     * Переопределение метода equals.
     *
     * @see MusicalInstrument
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        StringedInstrument that = (StringedInstrument) obj;
        return hasBow == that.hasBow;
    }

    @Override
    public Boolean canCarry() {
        return price < 300 && !isBroken;
    }

    @Override
    public String carry() throws CarryingException {
        if (canCarry())
            return String.format("%s можно транспортировать самостоятельно.", name);
        else throw new CarryingException(String.format("%s не следует транспортировать самостоятельно!", name));
    }

    @Override
    public String useAdditionalTool() {
        if (hasBow)
            return "Для игры на этом инструменте нужен смычок.";
        return "Для игры на этом инструменте не нужен смычок.";
    }
}


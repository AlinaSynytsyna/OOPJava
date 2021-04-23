package com.java.inheritance.hierarchy.implementation;

import com.java.inheritance.exceptions.BrokenInstrumentException;
import com.java.inheritance.exceptions.CarryingException;
import com.java.inheritance.hierarchy.base_class.MusicalInstrument;
import com.java.inheritance.hierarchy.base_class.Timbre;

/**
 * Класс-наследник - духовой инструмент.
 *
 * @see MusicalInstrument
 */
public final class WindInstrument extends MusicalInstrument {
    /**
     * Тип духового инструмента.
     */
    private WindInstrumentType windInstrumentType;

    /**
     * Конструктор класса.
     */
    public WindInstrument(String name, String timbre, double price, boolean isBroken, double volume, int type) {
        this.name = name;
        this.timbre = Timbre.valueOf(timbre.toUpperCase());
        if (price > 0)
            this.price = price;
        else this.price = 0;
        this.isBroken = isBroken;
        if (volume < 0)
            this.volume = 0;
        else this.volume = Math.min(volume, 1.5);
        this.windInstrumentType = type == 1 ? WindInstrumentType.COPPER : WindInstrumentType.WOODEN;
    }

    /**
     * Конструктор класса без параметров.
     */
    public WindInstrument() {
      super();
        windInstrumentType = WindInstrumentType.COPPER;
    }

    /**
     * Конструктор класса с основными параметрами.
     */
    public WindInstrument(String name, double price, boolean isBroken, int type) {
        this.name = name;
        timbre = Timbre.UNDEFINED;
        this.price = price;
        this.isBroken = isBroken;
        volume = 0;
        this.windInstrumentType = type == 1 ? WindInstrumentType.COPPER : WindInstrumentType.WOODEN;
    }

    /**
     * Возвращает значение поля windInstrumentType.
     */
    public String getWindInstrumentType() {
        return windInstrumentType.getType();
    }

    /**
     * Устанавливает тип духового инструмента.
     */
    public void setWindInstrumentType(int value) {
        windInstrumentType = value == 1 ? WindInstrumentType.COPPER : WindInstrumentType.WOODEN;
    }

    /**
     * Дунуть в духовой инструмент.
     */
    public void blow() {
        System.out.printf("Вы дунули в инструмент %s.\n", name);
    }

    /**
     * Выводит информацию о духовом инструменте.
     */
    @Override
    public String toString() {
        String result = String.format("Название духового инструмента: %s." +
                        " Тембр инструмента: %s." +
                        " Цена за единицу: %.2f. Тип духового инструмента: %s.\n",
                name,
                getTimbre(),
                price,
                getWindInstrumentType());
        if (isBroken)
            result += " Этот инструмент сломан и нуждается в починке!";
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
            throw new BrokenInstrumentException(String.format("Сначала необходимо починить духовой инструмент %s!\n", name));
        } else {
            String type = (windInstrumentType == WindInstrumentType.COPPER) ? "изменением силы вдуваемого воздуха" : "открытием отверстий на корпусе инструмента";
            System.out.printf("Духовой инструмент %s издает %s звук %s.\n", name, getTimbre(), type);
            System.out.printf("Громкость издаваемого звука: %.2f.\n", volume);
        }
    }

    /**
     * Переопределение метода базового класса.
     * Починить музыкальный инструмент.
     */
    @Override
    public void repairInstrument() {
        if (!isBroken)
            System.out.printf("Инструмент %s не нуждается в починке.\n", name);
        else {
            System.out.printf("Инструмент %s восстановлен, его стоимость возросла на 30 процентов.\n", name);
            price += price * 0.3;
            isBroken = false;
        }
    }

    /**
     * Переопределение метода базового класса.
     * Изменить громкость инструмента.
     */
    @Override
    public void changeVolume(double value) {
        if (value >= 0 && value <= 1.5)
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
        if (windInstrumentType == WindInstrumentType.COPPER) hash++;
        else hash += 2;
        return 15 * hash;
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
        WindInstrument that = (WindInstrument) obj;
        return windInstrumentType == that.windInstrumentType;
    }

    @Override
    public Boolean canCarry() {
        return price < 350 && !isBroken;
    }

    @Override
    public String carry() throws CarryingException {
        if(canCarry())
            return String.format("%s можно транспортировать самостоятельно.", name);
        else throw new CarryingException(String.format("%s не следует транспортировать самостоятельно!", name));
    }

    /**
     * Тип духового инструмента.
     * Обозначает, каким образом извлекается звук.
     * Медный - изменением силы вдуваемого воздуха.
     * Деревянный - открытием и закрытием отверстий на корпусе инструмента.
     */
    private enum WindInstrumentType {
        COPPER(1),
        WOODEN(2);
        /**
         * Числовое представление перечисления.
         */
        private final int type;

        /**
         * Конструктор, созданный для перечисления автоматически.
         */
        WindInstrumentType(int type) {
            this.type = type;
        }

        /**
         * Геттер строкового представления перечисления.
         */
        String getType() {
            if (type == 1)
                return "МЕДНЫЙ";
            else return "ДЕРЕВЯННЫЙ";
        }
    }
}

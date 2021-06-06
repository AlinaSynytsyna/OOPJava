package com.java.inheritance.hierarchy.implementation;

import com.java.inheritance.exceptions.BrokenInstrumentException;
import com.java.inheritance.exceptions.CarryingException;
import com.java.inheritance.hierarchy.base_class.MusicalInstrument;
import com.java.inheritance.hierarchy.base_class.Timbre;
import com.java.inheritance.hierarchy.interfaces.ICarriable;
import com.java.inheritance.hierarchy.interfaces.INeedAdditionalTools;


/**
 * Класс-наследник - ударный инструмент.
 *
 * @see MusicalInstrument
 */
public final class PercussionInstrument extends MusicalInstrument implements INeedAdditionalTools, ICarriable {

    /**
     * Нужны ли палочки для игры на ударном инструменте?
     */
    private boolean hasSticks;

    /**
     * Тип ударного инструмента.
     */
    private PercussionInstrumentType percussionInstrumentType;

    /**
     * Конструктор класса.
     */
    public PercussionInstrument(String name, String timbre, double price, boolean isBroken, double volume, boolean hasSticks, int instrumentType) {
        this.name = name;
        this.timbre = Timbre.valueOf(timbre.toUpperCase());
        if (price > 0)
            this.price = price;
        else this.price = 0;
        this.isBroken = isBroken;
        if (volume < 0)
            this.volume = 0;
        else this.volume = Math.min(volume, 2);
        this.hasSticks = hasSticks;
        this.percussionInstrumentType = instrumentType == 1 ? PercussionInstrumentType.MEMBRANOPHONE : PercussionInstrumentType.IDIOPHONE;
        isPlaying = false;
    }

    /**
     * Конструктор класса без параметров.
     */
    public PercussionInstrument() {
        super();
        hasSticks = false;
        percussionInstrumentType = PercussionInstrumentType.MEMBRANOPHONE;
    }

    /**
     * Конструктор класса с основными параметрами.
     */
    public PercussionInstrument(String name, double price, boolean isBroken, boolean hasSticks, int instrumentType) {
        this.name = name;
        timbre = Timbre.UNDEFINED;
        this.price = price;
        this.isBroken = isBroken;
        volume = 0;
        this.hasSticks = hasSticks;
        this.percussionInstrumentType = instrumentType == 1 ? PercussionInstrumentType.MEMBRANOPHONE : PercussionInstrumentType.IDIOPHONE;
        isPlaying = false;
    }

    /**
     * Возвращает значение поля hasSticks.
     */
    public boolean getHasSticks() {
        return hasSticks;
    }

    /**
     * Возвращает строковое значение поля percussionInstrumentType.
     */
    public String getPercussionInstrumentType() {
        return percussionInstrumentType.getType();
    }

    /**
     * Устанавливает значение поля hasSticks.
     */
    public void setHasSticks(boolean value) {
        hasSticks = value;
    }

    /**
     * Устанавливает значение типа ударного инструмента.
     *
     * @param value - числовое значение типа ударного инструмента.
     */
    public void setPercussionInstrumentType(int value) {
        percussionInstrumentType = value == 1 ? PercussionInstrumentType.MEMBRANOPHONE : PercussionInstrumentType.IDIOPHONE;
    }

    /**
     * Извлечь звук инструмента ударом по корпусу или по мембране.
     */
    public void extractSound() {
        String sticks = hasSticks ? "палочками" : "рукой";
        String type = getPercussionInstrumentType().equals("МЕМБРАНОФОН") ? "мембрану инструмента" : "корпус инструмента";
        System.out.printf("Вы ударили %s %s %s.\n", type, name, sticks);
    }

    /**
     * Выводит информацию об ударном инструменте.
     */
    @Override
    public String toString() {
        String result = String.format("Название ударного инструмента: %s." +
                        " Тембр инструмента: %s." +
                        " Цена за единицу: %.2f." +
                        " Тип ударного инструмента: %s.\n" + useAdditionalTool(),
                name,
                getTimbre(),
                price,
                getPercussionInstrumentType());
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
            throw new BrokenInstrumentException(String.format("Сначала необходимо починить ударный инструмент %s!\n", name));
        } else {
            String sticks = hasSticks ? "с помощью палочек" : "без помощи палочек";
            System.out.printf("Ударный инструмент %s издает %s звук %s.\n", name, getTimbre(), sticks);
            System.out.printf("Громкость издаваемого звука: %.2f.\n", volume);
        }
    }

    /**
     * Починить музыкальный инструмент.
     */
    @Override
    public void repairInstrument() {
        if (!isBroken)
            System.out.printf("Инструмент %s не нуждается в починке.\n", name);
        else {
            System.out.printf("Инструмент %s восстановлен, его стоимость возросла на 45 процентов.\n", name);
            price += price * 0.45;
            isBroken = false;
        }
    }

    /**
     * Изменить громкость инструмента.
     */
    @Override
    public void changeVolume(double value) {
        if (value >= 0 && value <= 2)
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
        if (hasSticks) hash++;
        else hash += 2;
        if (percussionInstrumentType == PercussionInstrumentType.MEMBRANOPHONE) hash++;
        else hash += 2;
        return 31 * hash;
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
        PercussionInstrument that = (PercussionInstrument) obj;
        if (this.hasSticks != that.hasSticks)
            return false;
        return percussionInstrumentType == that.percussionInstrumentType;
    }

    @Override
    public Boolean canCarry() {
        return price < 500 && !isBroken;
    }

    @Override
    public String carry() throws CarryingException {
        if (canCarry())
            return String.format("%s можно транспортировать самостоятельно.", name);
        else throw new CarryingException(String.format("%s не следует транспортировать самостоятельно!", name));
    }

    @Override
    public String useAdditionalTool() {
        if (hasSticks)
            return "Для игры на этом инструменте нужны палочки.";
        return "Для игры на этом инструменте не нужны палочки.";
    }

    /**
     * Тип ударного инструмента.
     * Обозначает, что является звучащим телом в инструменте.
     * Мембранофон - звучащим телом ялвяется натянутая поверхность.
     * Идиофон - звучащим телом является весь инструмент.
     */
    private enum PercussionInstrumentType {
        MEMBRANOPHONE(1),
        IDIOPHONE(2);
        /**
         * Числовое представление перечисления.
         */
        private final int type;

        /**
         * Конструктор, созданный для перечисления автоматически.
         */
        PercussionInstrumentType(int type) {
            this.type = type;
        }

        /**
         * Геттер строкового представления перечисления.
         */
        String getType() {
            if (type == 1)
                return "МЕМБРАНОФОН";
            else return "ИДИОФОН";
        }
    }
}


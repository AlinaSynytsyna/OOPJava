package com.java.inheritance.hierarchy.base_class;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.java.inheritance.exceptions.BrokenInstrumentException;
import com.java.inheritance.hierarchy.implementation.PercussionInstrument;
import com.java.inheritance.hierarchy.implementation.StringedInstrument;
import com.java.inheritance.hierarchy.implementation.WindInstrument;
import com.java.inheritance.hierarchy.interfaces.ICarriable;

import java.io.Serializable;

/**
 * Базовый абстрактный класс - музыкальный инструмент.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringedInstrument.class, name = "Stringed"),
        @JsonSubTypes.Type(value = WindInstrument.class, name = "Wind"),
        @JsonSubTypes.Type(value = PercussionInstrument.class, name = "Percussion")}
)
public abstract class MusicalInstrument implements ICarriable, Serializable {
    /**
     * Название музыкального инструмента.
     */
    protected String name;
    /**
     * Цена музыкального инструмента.
     */
    protected double price;
    /**
     * Тембр музыкального инструмента.
     */
    protected Timbre timbre;
    /**
     * Сломанный ли этот инструмент?
     */
    protected boolean isBroken;
    /**
     * Громкость инструмента.
     */
    protected double volume;

    public MusicalInstrument() {
        name = "";
        timbre = Timbre.UNDEFINED;
        price = 0;
        isBroken = false;
        volume = 0;
    }

    /**
     * Возвращает значение поля name.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает значение поля price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Возвращает строковое значение поля timbre.
     */
    public String getTimbre() {
        return timbre.getTimbre().toUpperCase();
    }

    /**
     * Возвращает значение поля isBroken.
     */
    public boolean getIsBroken() {
        return isBroken;
    }

    /**
     * Возвращает значение поля volume.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Устанавливает значение поля name.
     */
    public void setName(String value) {
        name = value;
    }

    /**
     * Устанавливает значение поля price.
     */
    public void setPrice(double value) {
        if (value > 0)
            price = value;
    }

    /**
     * Устанавливает значение поля timbre.
     */
    public void setTimbre(String value) {
        timbre = Timbre.valueOf(value.toUpperCase());
    }

    /**
     * Устанавливает значение поля isBroken.
     */
    public void setIsBroken(boolean value) {
        isBroken = value;
    }

    /**
     * Вывести информацию о проигрвываемом звуке и способе получения.
     */
    public abstract void playSound() throws BrokenInstrumentException;

    /**
     * Починить музыкальный инструмент.
     */
    public abstract void repairInstrument();

    /**
     * Изменить громкость инструмента.
     */
    public abstract void changeVolume(double value);

    /**
     * Переопределение метода hashCode.
     */
    @Override
    public int hashCode() {
        int hash = 1;
        for (char c : name.toCharArray()) {
            hash += c;
        }
        if (timbre == Timbre.LOW)
            hash++;
        else if (timbre == Timbre.MEDIUM)
            hash += 2;
        else if (timbre == Timbre.HIGH)
            hash += 3;
        else if (timbre == Timbre.UNDEFINED)
            hash += 5;
        if (isBroken)
            hash++;
        hash += (int) price;
        hash += (int) volume;
        return hash;
    }

    /**
     * Переопределение метода equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        final MusicalInstrument other = (MusicalInstrument) obj;
        if (!this.name.equals(other.name))
            return false;
        if (this.price != other.price)
            return false;
        if (this.timbre != other.timbre)
            return false;
        if (this.isBroken != other.isBroken)
            return false;
        return this.volume == other.volume;
    }
}

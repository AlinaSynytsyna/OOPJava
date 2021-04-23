package com.java.inheritance.hierarchy.interfaces;

import com.java.inheritance.exceptions.CarryingException;

/**
 * Интерфейс, который определяет,
 * можно ли транспортировать инструмент самостоятельно.
 */
public interface ICarriable {
    Boolean canCarry();
    String carry() throws CarryingException;
}

package com.java.inheritance.hierarchy.base_class;

/**
 * Перечисление Тембр.
 */
public enum Timbre {
    LOW("low"),
    MEDIUM("medium"),
    HIGH("high"),
    UNDEFINED("undefined");

    /**
     * Строковое представление значения перечисления.
     */
    private final String timbre;

    /**
     * Конструктор, созданный для перечисления автоматически.
     */
    Timbre(String timbre) {
        this.timbre = timbre;
    }

    /**
     * Геттер для строкового представления перечисления.
     */
    String getTimbre() {
        switch (timbre) {
            case "low":
                return "низкий";
            case "medium":
                return "средний";
            case "high":
                return "высокий";
            default:
                return "неопределенный";
        }
    }
}
package org.example;

/**
 * Главный класс приложения для запуска тестов производительности.
 * Содержит точку входа в программу и демонстрирует работу
 * сравнительного тестирования ArrayList и LinkedList.
 *
 * @author ilabe
 * @version 1.0
 */
public class Main
{
    /**
     * Точка входа в приложение.
     * Запускает комплексное тестирование производительности коллекций.
     * Выводит подробный отчет в консоль.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args)
    {
        String testReport = ListPerformanceTest.runAllTests();
        System.out.println(testReport);
    }
}
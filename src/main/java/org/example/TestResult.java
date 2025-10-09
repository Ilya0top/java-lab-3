package org.example;

/**
 * Класс для хранения результатов тестирования производительности.
 * Содержит данные о времени выполнения операций для ArrayList и LinkedList.
 *
 * @author ilabe
 * @version 1.0
 */
public class TestResult
{
    /** Название тестируемого метода */
    private String methodName;

    /** Тип выполняемой операции */
    private String operationType;

    /** Количество выполненных операций */
    private int operationCount;

    /** Время выполнения операций для ArrayList (в наносекундах) */
    private long arrayListTime;

    /** Время выполнения операций для LinkedList (в наносекундах) */
    private long linkedListTime;

    /**
     * Конструктор для создания объекта с результатами тестирования.
     *
     * @param methodName название тестируемого метода
     * @param operationType тип выполняемой операции
     * @param operationCount количество выполненных операций
     * @param arrayListTime время выполнения для ArrayList (в наносекундах)
     * @param linkedListTime время выполнения для LinkedList (в наносекундах)
     */
    public TestResult(String methodName, String operationType, int operationCount, long arrayListTime, long linkedListTime)
    {
        this.methodName = methodName;
        this.operationType = operationType;
        this.operationCount = operationCount;
        this.arrayListTime = arrayListTime;
        this.linkedListTime = linkedListTime;
    }

    /**
     * Возвращает название тестируемого метода.
     *
     * @return название метода (add, get, remove, contains, etc.)
     */
    public String getMethodName()
    {
        return methodName;
    }

    /**
     * Возвращает тип выполняемой операции.
     *
     * @return тип операции (add to end, get by index, remove from start, etc.)
     */
    public String getOperationType()
    {
        return operationType;
    }

    /**
     * Возвращает количество выполненных операций.
     *
     * @return количество операций, выполненных в тесте
     */
    public int getOperationCount()
    {
        return operationCount;
    }

    /**
     * Возвращает время выполнения операций для ArrayList.
     *
     * @return время выполнения в наносекундах
     */
    public long getArrayListTime()
    {
        return arrayListTime;
    }

    /**
     * Возвращает время выполнения операций для LinkedList.
     *
     * @return время выполнения в наносекундах
     */
    public long getLinkedListTime()
    {
        return linkedListTime;
    }
}
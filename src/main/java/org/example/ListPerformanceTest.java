package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для сравнения производительности ArrayList и LinkedList.
 * Выполняет замеры времени различных операций над коллекциями.
 *
 * @author ilabe
 * @version 1.0
 */
public class ListPerformanceTest
{
    /** Количество операций для выполнения  в каждом тесте */
    private static final int OPERATION_COUNT = 10000;

    /** Список храненияя результатов тестирования */
    private static List<TestResult> results = new ArrayList<>();

    /**
     * Запускает все тесты производительности и возвращает отчёт.
     * Вполняет сравнения ArrayList и LinkedList по различным операциям.
     *
     * @return строка с подробным отчётом о производительности
     */
    public static String runAllTests()
    {
        StringBuilder report = new StringBuilder();

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        results.clear();

        testAddToEnd(arrayList, linkedList);
        testAddToBeginning(arrayList, linkedList);
        testAddToMiddle(arrayList, linkedList);
        testGet(arrayList, linkedList);
        testRemoveFromEnd(arrayList, linkedList);
        testRemoveFromBeginning(arrayList, linkedList);
        testRemoveFromMiddle(arrayList, linkedList);
        testIteration(arrayList, linkedList);
        testContains(arrayList, linkedList);
        testClear(arrayList, linkedList);


        report.append("Comparison of ArrayList and LinkedList performance\n");
        report.append("Number of operations: ").append(OPERATION_COUNT).append("\n");
        report.append("==================================================\n\n");
        report.append(generateResultsTable());

        return report.toString();
    }

    /**
     * Генерирует отформатированную таблицу с результатами тестирования.
     * Содержит сравнение времени выполнения операций для ArrayList и LinkedList
     *
     * @return форматированная таблица с результатами тестов
     */
    public static String generateResultsTable()
    {
        StringBuilder table = new StringBuilder();

        table.append("=".repeat(120)).append("\n");
        table.append("ARRAYLIST vs LINKEDLIST PERFORMANCE COMPARISON\n");
        table.append("=".repeat(120)).append("\n");

        table.append(String.format("| %-8s | %-18s | %-8s | %-16s | %-16s | %-24s |%n",
                "Method", "Operation Type", "Count", "ArrayList(ns)", "LinkedList(ns)", "Performance"));
        table.append("-".repeat(120)).append("\n");

        for (TestResult result : results)
        {
            String winner;
            double ratio;

            if (result.getArrayListTime() < result.getLinkedListTime())
            {
                ratio = (double) result.getLinkedListTime() / result.getArrayListTime();
                winner = "ArrayList " + String.format("%.1f", ratio) + "x faster";
            } else
            {
                ratio = (double) result.getArrayListTime() / result.getLinkedListTime();
                winner = "LinkedList " + String.format("%.1f", ratio) + "x faster";
            }

            table.append(String.format("| %-8s | %-18s | %-8d | %16d | %16d | %-24s |%n",
                    result.getMethodName(),
                    result.getOperationType(),
                    result.getOperationCount(),
                    result.getArrayListTime(),
                    result.getLinkedListTime(),
                    winner));
        }
        table.append("=".repeat(120)).append("\n");

        return table.toString();
    }

    /**
     * Заполнет обе коллекции тестовми данными.
     *
     * @param arrayList коллекци ArrayList для заполнения
     * @param linkedList коллекци LinkedList для заполнения
     * @param count количество элементов для добавления
     */
    public static void fillLists(List<Integer> arrayList, List<Integer> linkedList, int count)
    {
        arrayList.clear();
        linkedList.clear();
        for (int i = 0; i < count; i++)
        {
            arrayList.add(i);
            linkedList.add(i);
        }
    }

    /**
     * Тестирует производительность добавления элементов в конец коллекций.
     *
     * @param arrayList тестируемая коллекция ArrayList
     * @param linkedList тестируемая коллекция LinkedList
     */
    public static void testAddToEnd(List<Integer> arrayList, List<Integer> linkedList)
    {
        arrayList.clear();
        linkedList.clear();

        long startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT; i++)
        {
            arrayList.add(i);
        }
        long arrayListTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT; i++)
        {
            linkedList.add(i);
        }
        long linkedListTime = System.nanoTime() - startTime;

        results.add(new TestResult("add", "add to end", OPERATION_COUNT, arrayListTime, linkedListTime));
    }

    /**
     * Тестирует производительность добавления элементов в начало коллекций.
     *
     * @param arrayList тестируемая коллекция ArrayList
     * @param linkedList тестируемая коллекция LinkedList
     */
    public static void testAddToBeginning(List<Integer> arrayList, List<Integer> linkedList)
    {
        arrayList.clear();
        linkedList.clear();

        long startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT; i++)
        {
            arrayList.add(0, i);
        }
        long arrayListTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT; i++)
        {
            linkedList.add(0, i);
        }
        long linkedListTime = System.nanoTime() - startTime;

        results.add(new TestResult("add", "add to start", OPERATION_COUNT, arrayListTime, linkedListTime));
    }

    /**
     * Тестирует производительность добавления элементов в середину коллекций.
     * Использует уменьшенное количество операций из-за высокой сложности.
     *
     * @param arrayList тестируемая коллекция ArrayList
     * @param linkedList тестируемая коллекция LinkedList
     */
    public static void testAddToMiddle(List<Integer> arrayList, List<Integer> linkedList)
    {
        arrayList.clear();
        linkedList.clear();

        for (int i = 0; i < 1000; i++)
        {
            arrayList.add(i);
            linkedList.add(i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT / 10; i++)
        {
            arrayList.add(arrayList.size() / 2, i);
        }
        long arrayListTime = System.nanoTime() - startTime;

        arrayList.clear();
        linkedList.clear();
        for (int i = 0; i < 1000; i++)
        {
            arrayList.add(i);
            linkedList.add(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT / 10; i++)
        {
            linkedList.add(linkedList.size() / 2, i);
        }
        long linkedListTime = System.nanoTime() - startTime;

        results.add(new TestResult("add", "add to middle", OPERATION_COUNT / 10, arrayListTime, linkedListTime));
    }

    /**
     * Тестирует производительность получения элементов по индексу.
     *
     * @param arrayList тестируемая коллекция ArrayList
     * @param linkedList тестируемая коллекция LinkedList
     */
    public static void testGet(List<Integer> arrayList, List<Integer> linkedList)
    {
        fillLists(arrayList, linkedList, OPERATION_COUNT);

        long startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT; i++)
        {
            arrayList.get(i);
        }
        long arrayListTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT; i++)
        {
            linkedList.get(i);
        }
        long linkedListTime = System.nanoTime() - startTime;

        results.add(new TestResult("get", "get by index", OPERATION_COUNT, arrayListTime, linkedListTime));
    }

    /**
     * Тестирует производительность удаления элементов с конца коллекций.
     *
     * @param arrayList тестируемая коллекция ArrayList
     * @param linkedList тестируемая коллекция LinkedList
     */
    public static void testRemoveFromEnd(List<Integer> arrayList, List<Integer> linkedList)
    {
        fillLists(arrayList, linkedList, OPERATION_COUNT);

        long startTime = System.nanoTime();
        for (int i = OPERATION_COUNT - 1; i >= 0; i--)
        {
            arrayList.remove(i);
        }
        long arrayListTime = System.nanoTime() - startTime;

        fillLists(arrayList, linkedList, OPERATION_COUNT);
        startTime = System.nanoTime();
        for (int i = OPERATION_COUNT - 1; i >= 0; i--)
        {
            linkedList.remove(i);
        }
        long linkedListTime = System.nanoTime() - startTime;

        results.add(new TestResult("remove", "remove from end", OPERATION_COUNT, arrayListTime, linkedListTime));
    }

    /**
     * Тестирует производительность удаления элементов с начала коллекций.
     *
     * @param arrayList тестируемая коллекция ArrayList
     * @param linkedList тестируемая коллекция LinkedList
     */
    public static void testRemoveFromBeginning(List<Integer> arrayList, List<Integer> linkedList)
    {
        fillLists(arrayList, linkedList, OPERATION_COUNT);

        long startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT; i++)
        {
            arrayList.remove(0);
        }
        long arrayListTime = System.nanoTime() - startTime;

        fillLists(arrayList, linkedList, OPERATION_COUNT);
        startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT; i++)
        {
            linkedList.remove(0);
        }
        long linkedListTime = System.nanoTime() - startTime;

        results.add(new TestResult("remove", "remove from start", OPERATION_COUNT, arrayListTime, linkedListTime));
    }

    /**
     * Тестирует производительность удаления элементов из середины коллекций.
     * Использует уменьшенное количество операций из-за высокой сложности.
     *
     * @param arrayList тестируемая коллекция ArrayList
     * @param linkedList тестируемая коллекция LinkedList
     */
    public static void testRemoveFromMiddle(List<Integer> arrayList, List<Integer> linkedList)
    {
        fillLists(arrayList, linkedList, OPERATION_COUNT / 2);

        long startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT / 20; i++)
            if (!arrayList.isEmpty())
                arrayList.remove(arrayList.size() / 2);

        long arrayListTime = System.nanoTime() - startTime;

        fillLists(arrayList, linkedList, OPERATION_COUNT / 2);
        startTime = System.nanoTime();
        for (int i = 0; i < OPERATION_COUNT / 20; i++)
            if (!linkedList.isEmpty())
                linkedList.remove(linkedList.size() / 2);

        long linkedListTime = System.nanoTime() - startTime;

        results.add(new TestResult("remove", "remove from middle", OPERATION_COUNT / 20, arrayListTime, linkedListTime));
    }

    /**
     * Тестирует производительность итерации по коллекциям.
     * Сравнивает два подхода: for-loop и iterator.
     *
     * @param arrayList тестируемая коллекция ArrayList
     * @param linkedList тестируемая коллекция LinkedList
     */
    public static void testIteration(List<Integer> arrayList, List<Integer> linkedList)
    {
        fillLists(arrayList, linkedList, OPERATION_COUNT);

        long startTime = System.nanoTime();
        for (int i = 0; i < arrayList.size(); i++)
        {
            int value = arrayList.get(i);
        }
        long arrayListTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < linkedList.size(); i++)
        {
            int value = linkedList.get(i);
        }
        long linkedListTimeFor = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int value : linkedList)
        {

        }
        long linkedListTimeIterator = System.nanoTime() - startTime;

        results.add(new TestResult("iterate", "for-loop", OPERATION_COUNT, arrayListTime, linkedListTimeFor));
        results.add(new TestResult("iterate", "iterator", OPERATION_COUNT, arrayListTime, linkedListTimeIterator));
    }

    /**
     * Тестирует производительность поиска элементов в коллекциях.
     * Проверяет элементы в разных позициях: начало, середина, конец, отсутствующий.
     *
     * @param arrayList тестируемая коллекция ArrayList
     * @param linkedList тестируемая коллекция LinkedList
     */
    public static void testContains(List<Integer> arrayList, List<Integer> linkedList)
    {
        fillLists(arrayList, linkedList, OPERATION_COUNT);

        int firstElement = 0;
        int middleElement = OPERATION_COUNT / 2;
        int lastElement = OPERATION_COUNT - 1;
        int notFoundElement = OPERATION_COUNT + 1000;

        long startTime = System.nanoTime();
        arrayList.contains(firstElement);
        arrayList.contains(middleElement);
        arrayList.contains(lastElement);
        arrayList.contains(notFoundElement);
        long arrayListTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        linkedList.contains(firstElement);
        linkedList.contains(middleElement);
        linkedList.contains(lastElement);
        linkedList.contains(notFoundElement);
        long linkedListTime = System.nanoTime() - startTime;

        results.add(new TestResult("contains", "various positions", 4, arrayListTime, linkedListTime));
    }

    /**
     * Тестирует производительность полной очистки коллекций.
     *
     * @param arrayList тестируемая коллекция ArrayList
     * @param linkedList тестируемая коллекция LinkedList
     */
    public static void testClear(List<Integer> arrayList, List<Integer> linkedList)
    {
        fillLists(arrayList, linkedList, OPERATION_COUNT);

        long startTime = System.nanoTime();
        arrayList.clear();
        long arrayListTime = System.nanoTime() - startTime;

        fillLists(arrayList, linkedList, OPERATION_COUNT);
        startTime = System.nanoTime();
        linkedList.clear();
        long linkedListTime = System.nanoTime() - startTime;

        results.add(new TestResult("clear", "all elements", OPERATION_COUNT, arrayListTime, linkedListTime));
    }

    /**
     * Возвращает количество операций, используемое в тестах.
     *
     * @return количество операций для каждого теста
     */
    public static int getOperationCount()
    {
        return OPERATION_COUNT;
    }
}
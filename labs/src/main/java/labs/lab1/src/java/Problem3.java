package labs.lab1.src.java;

public class Problem3 {

    /**
     * Метод solveKnapsackProblem решает классическую задачу о рюкзаке:
     * https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B4%D0%B0%D1%87%D0%B0_%D0%BE_%D1%80%D1%8E%D0%BA%D0%B7%D0%B0%D0%BA%D0%B5
     * <p>
     * Пусть даны n предметов с индексами 0, 1, ..., n-1.
     *
     * @param values                массив положительных чисел длины n, определяющий ценности n предметов
     * @param weights               массив положительных чисел длины n, определяющий массы n предметов
     * @param maximumWeightCapacity положительное число, определяющее грузоподъемность рюкзака
     * @param numberOfThings        положительное число, определяющее число вещей. Используется для управления рекурсивными вызовами
     * @return максимальное значение общей ценности предметов, которые можно положить в рюкзак с учетом ограничения
     * на грузоподъемность.
     * <p>
     * ПРИМЕР:
     * Вход: values = [2.0, 1.0, 5.0]
     * weights = [1.0, 1.0, 2.0]
     * maximumWeightCapacity = 2.5
     * Выход: 5.0 (максимальная ценность достигается при выборе одного предмета с индексом 2)
     */

    //Решение из динамического программирования не смог придумать, сделал через рекурсию
    public static double solveKnapsackProblem(double[] values, double[] weights, double maximumWeightCapacity, int numberOfThings) {
        if((numberOfThings == 0) || (maximumWeightCapacity == 0) || (values.length != weights.length))
            return 0;

        if (weights[numberOfThings - 1] > maximumWeightCapacity)
            return solveKnapsackProblem(values, weights, maximumWeightCapacity, numberOfThings - 1);

        double a = values[numberOfThings - 1] + solveKnapsackProblem(values, weights, maximumWeightCapacity - weights[numberOfThings - 1], numberOfThings - 1);
        double b = solveKnapsackProblem(values, weights, maximumWeightCapacity, numberOfThings - 1);
        return Math.max(a, b);
    }
}

package by.it.group410972.grigorovich.lesson01;

import java.math.BigInteger;

/*
 * Задание:
 * Реализовать рекурсивный способ вычисления чисел Фибоначчи.
 *
 * Метод calc(int n) должен возвращать n-е число Фибоначчи с помощью рекурсии (тип int).
 * Метод slowA(Integer n) должен возвращать n-е число Фибоначчи с помощью рекурсии (тип BigInteger).
 *
 * Формула:
 * F(n) = F(n - 1) + F(n - 2)
 * F(0) = 0
 * F(1) = 1
 *
 * Внимание: у рекурсивного метода экспоненциальная сложность — использовать только для малых значений n!
 */

public class FiboA {

    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboA fibo = new FiboA();
        int n = 33;
        System.out.printf("calc(%d)=%d \n\t time=%d \n\n", n, fibo.calc(n), fibo.time());

        // вычисление чисел Фибоначчи медленным методом (рекурсией)
        fibo = new FiboA();
        n = 34;
        System.out.printf("slowA(%d)=%d \n\t time=%d \n\n", n, fibo.slowA(n), fibo.time());
    }

    private long time() {
        long res = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        return res;
    }

    /**
     * Рекурсивный метод с int (ограничен по размеру, работает до ~46)
     * Время выполнения: O(2^n)
     */
    private int calc(int n) {
        if (n <= 1) {
            return n;
        }
        return calc(n - 1) + calc(n - 2);
    }

    /**
     * Рекурсивный метод с BigInteger (можно вычислять большие значения)
     * Время выполнения: O(2^n)
     */
    BigInteger slowA(Integer n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        return slowA(n - 1).add(slowA(n - 2));
    }
}
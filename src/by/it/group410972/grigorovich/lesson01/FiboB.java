package by.it.group410972.grigorovich.lesson01;

import java.math.BigInteger;

/*
 * Задание:
 * Реализовать способ вычисления чисел Фибоначчи со вспомогательным массивом,
 * без ограничений на размер результата (использовать BigInteger).
 *
 * Требования:
 * - Время: O(n)
 * - Память: O(n)
 *
 * Формула:
 * F(0) = 0
 * F(1) = 1
 * F(n) = F(n-1) + F(n-2)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        // вычисление чисел Фибоначчи быстрым методом с массивом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Быстрый способ вычисления чисел Фибоначчи с массивом.
     * Время: O(n), Память: O(n)
     */
    BigInteger fastB(Integer n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger[] fib = new BigInteger[n + 1];
        fib[0] = BigInteger.ZERO;
        fib[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1].add(fib[i - 2]);
        }

        return fib[n];
    }
}

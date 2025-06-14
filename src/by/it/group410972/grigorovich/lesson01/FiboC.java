package by.it.group410972.grigorovich.lesson01;

/*
 * Даны целые числа 1 <= n <= 1E18 и 2 <= m <= 1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * Время расчета должно быть не более 2 секунд.
 *
 * Используется Пизано-период (Pisano Period), чтобы сократить вычисления.
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        long n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d) mod %d = %d \n\t time=%d ms\n\n", n, m, fibo.fasterC(n, m), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Вычисление остатка от деления n-го числа Фибоначчи на m.
     * Использует Пизано-период.
     */
    long fasterC(long n, int m) {
        // 1. Найдём Пизано-период для m
        int pisanoPeriod = getPisanoPeriod(m);

        // 2. Сократим n по модулю длины периода
        int reducedN = (int)(n % pisanoPeriod);

        // 3. Найдём reducedN-е число Фибоначчи по модулю m
        return fibMod(reducedN, m);
    }

    // Вычисление числа Фибоначчи по модулю m
    private long fibMod(int n, int m) {
        if (n <= 1) return n;

        long prev = 0;
        long curr = 1;

        for (int i = 2; i <= n; i++) {
            long temp = (prev + curr) % m;
            prev = curr;
            curr = temp;
        }

        return curr;
    }

    // Вычисление длины Пизано-периода для модуля m
    private int getPisanoPeriod(int m) {
        long prev = 0;
        long curr = 1;

        for (int i = 0; i < m * 6; i++) {
            long temp = (prev + curr) % m;
            prev = curr;
            curr = temp;

            // Период всегда начинается с 01
            if (prev == 0 && curr == 1) {
                return i + 1;
            }
        }

        return 0; // fallback, но этого не должно произойти
    }
}
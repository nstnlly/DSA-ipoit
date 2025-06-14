package by.it.group410972.grigorovich.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_LongDivComSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_LongDivComSubSeq.class.getResourceAsStream("dataB.txt");
        B_LongDivComSubSeq instance = new B_LongDivComSubSeq();
        int result = instance.getDivSeqSize(stream);
        System.out.print(result);
    }

    int getDivSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        int[] dp = new int[n];
        int result = 0;

        // Инициализация: минимальная длина подпоследовательности для каждого элемента - 1
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Заполнение dp:
        // dp[i] = max длина подпоследовательности с делимостью, заканчивающейся на m[i]
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[i] % m[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Поиск максимальной длины
        for (int length : dp) {
            if (length > result) {
                result = length;
            }
        }

        return result;
    }
}
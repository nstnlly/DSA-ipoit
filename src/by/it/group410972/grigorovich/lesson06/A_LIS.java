package by.it.group410972.grigorovich.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_LIS {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_LIS.class.getResourceAsStream("dataA.txt");
        A_LIS instance = new A_LIS();
        int result = instance.getSeqSize(stream);
        System.out.print(result);
    }

    int getSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        int[] dp = new int[n];
        int result = 0;

        // Инициализируем dp — длина LIS, заканчивающейся в i
        for (int i = 0; i < n; i++) {
            dp[i] = 1;  // минимальная длина LIS для каждого элемента - 1 (сам элемент)
        }

        // Строим dp
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[j] < m[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Максимальное значение в dp — длина наибольшей возрастающей подпоследовательности
        for (int length : dp) {
            if (length > result) {
                result = length;
            }
        }

        return result;
    }
}
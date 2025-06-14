package by.it.group410972.grigorovich.lesson06;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_LongNotUpSubSeq {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_LongNotUpSubSeq.class.getResourceAsStream("dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.println(result);
    }

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }

        // dp[i] — длина Невозрастающей подпоследовательности, заканчивающейся в i-ом элементе
        int[] dp = new int[n];
        // prev[i] — индекс предыдущего элемента в подпоследовательности для восстановления пути
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
        }

        int maxLen = 1;
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (m[j] >= m[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        // Восстановление индексов подпоследовательности (обратный порядок)
        int[] path = new int[maxLen];
        int currentIndex = maxIndex;
        for (int i = maxLen - 1; i >= 0; i--) {
            path[i] = currentIndex + 1; // +1 для перехода к 1-базированной индексации
            currentIndex = prev[currentIndex];
        }

        // Вывод результата
        System.out.println(maxLen);
        for (int i = 0; i < maxLen; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();

        return maxLen;
    }
}
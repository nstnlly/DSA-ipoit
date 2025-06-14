package by.it.group410972.grigorovich.lesson08;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_Stairs {

    int getMaxSum(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        int[] stairs = new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }

        // dp[i] — максимальная сумма при достижении i-й ступеньки (0-based)
        int[] dp = new int[n];

        if (n == 1) {
            return stairs[0];
        }

        dp[0] = stairs[0];
        dp[1] = Math.max(stairs[0] + stairs[1], stairs[1]);

        for (int i = 2; i < n; i++) {
            // можно прийти либо с i-1 ступеньки, либо с i-2, выбираем максимум
            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + stairs[i];
        }

        // Результат — максимум суммы на последней ступеньке
        return dp[n - 1];
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_Stairs.class.getResourceAsStream("dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        System.out.println(res);
    }

}
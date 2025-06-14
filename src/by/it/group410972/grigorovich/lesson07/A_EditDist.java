package by.it.group410972.grigorovich.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_EditDist {

    // Вспомогательный метод для рекурсивного вычисления расстояния Левенштейна с мемоизацией
    private int editDistRec(String one, String two, int i, int j, int[][] memo) {
        // База рекурсии
        if (i == one.length()) return two.length() - j; // нужно вставить оставшиеся символы two
        if (j == two.length()) return one.length() - i; // нужно удалить оставшиеся символы one

        if (memo[i][j] != -1) return memo[i][j];

        if (one.charAt(i) == two.charAt(j)) {
            // символы совпадают, переходим дальше без изменений
            memo[i][j] = editDistRec(one, two, i + 1, j + 1, memo);
        } else {
            // Рассматриваем три операции: вставка, удаление, замена
            int insert = editDistRec(one, two, i, j + 1, memo) + 1;
            int delete = editDistRec(one, two, i + 1, j, memo) + 1;
            int replace = editDistRec(one, two, i + 1, j + 1, memo) + 1;

            memo[i][j] = Math.min(insert, Math.min(delete, replace));
        }

        return memo[i][j];
    }

    int getDistanceEdinting(String one, String two) {
        int n = one.length();
        int m = two.length();

        // Инициализируем массив для мемоизации значением -1
        int[][] memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = -1;
            }
        }

        return editDistRec(one, two, 0, 0, memo);
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_EditDist.class.getResourceAsStream("dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
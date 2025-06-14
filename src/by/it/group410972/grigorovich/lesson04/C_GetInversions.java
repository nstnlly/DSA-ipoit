package by.it.group410972.grigorovich.lesson04;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_GetInversions {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_GetInversions.class.getResourceAsStream("dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        int result = instance.calc(stream);
        System.out.print(result);
    }

    int calc(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // Читаем размер массива
        int n = scanner.nextInt();
        int[] a = new int[n];

        // Читаем сам массив
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // Вспомогательный массив для сортировки
        int[] temp = new int[n];

        // Вызываем сортировку и подсчёт инверсий
        return mergeSortAndCount(a, temp, 0, n - 1);
    }

    // Метод сортировки слиянием и подсчёта инверсий
    int mergeSortAndCount(int[] array, int[] temp, int left, int right) {
        int invCount = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            // Инверсии в левой половине
            invCount += mergeSortAndCount(array, temp, left, mid);

            // Инверсии в правой половине
            invCount += mergeSortAndCount(array, temp, mid + 1, right);

            // Инверсии при слиянии двух половин
            invCount += merge(array, temp, left, mid, right);
        }

        return invCount;
    }

    // Метод слияния двух отсортированных частей массива и подсчёта инверсий
    int merge(int[] array, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        int invCount = 0;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
                invCount += (mid - i + 1); // Все оставшиеся элементы в левой половине — инверсии
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        // Копируем обратно в исходный массив
        for (i = left; i <= right; i++) {
            array[i] = temp[i];
        }

        return invCount;
    }
}
package by.it.group410972.grigorovich.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_CountSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_CountSort.class.getResourceAsStream("dataB.txt");
        B_CountSort instance = new B_CountSort();
        int[] result = instance.countSort(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] countSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        int[] points = new int[n];
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextInt();
        }

        // Максимальное значение в массиве (условие <=10)
        int maxVal = 10;
        int[] count = new int[maxVal + 1]; // для индексов от 0 до 10

        // Подсчитываем количество каждого числа
        for (int num : points) {
            count[num]++;
        }

        // Перезаписываем исходный массив отсортированными элементами
        int index = 0;
        for (int value = 1; value <= maxVal; value++) {
            for (int c = 0; c < count[value]; c++) {
                points[index++] = value;
            }
        }

        return points;
    }
}
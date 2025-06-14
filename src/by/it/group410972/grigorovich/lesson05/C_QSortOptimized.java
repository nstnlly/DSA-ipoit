package by.it.group410972.grigorovich.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        // Сортируем сегменты по start с помощью быстрой сортировки 3-way
        quickSort3Way(segments, 0, segments.length - 1);

        // Для каждой точки считаем сколько отрезков её покрывают
        for (int i = 0; i < m; i++) {
            int p = points[i];
            // Найдём индекс первого сегмента, у которого start > p — с помощью бинарного поиска
            int idx = binarySearchFirstGreater(segments, p);
            // Проходим по сегментам с меньшим или равным start и считаем те, где p <= stop
            int count = 0;
            // Идём назад от idx-1 к 0, проверяем, покрывает ли отрезок точку p
            for (int j = idx - 1; j >= 0; j--) {
                if (segments[j].start <= p && p <= segments[j].stop) {
                    count++;
                } else {
                    // Если отрезок начинается слишком рано, можно прервать — поскольку отсортировано по start,
                    // более ранние тоже не покроют (start ещё меньше, но точка p уже вне отрезка)
                    // Однако точка может быть меньше start, если start очень маленький, поэтому нельзя здесь прерывать по start
                    // В общем случае остановимся только если start > p (не может быть, так как идём назад),
                    // поэтому не прерываем.
                }
            }
            result[i] = count;
        }

        return result;
    }

    // Быстрая сортировка с 3-разбиением (Dutch National Flag)
    private void quickSort3Way(Segment[] arr, int low, int high) {
        while (low < high) {
            int lt = low, gt = high;
            Segment pivot = arr[low];
            int i = low + 1;
            while (i <= gt) {
                int cmp = arr[i].compareTo(pivot);
                if (cmp < 0) {
                    swap(arr, lt++, i++);
                } else if (cmp > 0) {
                    swap(arr, i, gt--);
                } else {
                    i++;
                }
            }
            // Теперь arr[low..lt-1] < pivot = arr[lt..gt] < arr[gt+1..high]
            // Сортируем меньшую часть рекурсивно, меньшую из двух частей, чтобы оптимизировать стек
            if (lt - low < high - gt) {
                quickSort3Way(arr, low, lt - 1);
                low = gt + 1; // хвостовая рекурсия по правой части
            } else {
                quickSort3Way(arr, gt + 1, high);
                high = lt - 1; // хвостовая рекурсия по левой части
            }
        }
    }

    // Бинарный поиск индекса первого сегмента, у которого start > point
    private int binarySearchFirstGreater(Segment[] arr, int point) {
        int low = 0;
        int high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid].start > point) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private void swap(Segment[] arr, int i, int j) {
        Segment temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            // Гарантируем, что start <= stop (на всякий случай)
            if (start <= stop) {
                this.start = start;
                this.stop = stop;
            } else {
                this.start = stop;
                this.stop = start;
            }
        }

        @Override
        public int compareTo(Segment o) {
            // Сортируем по start, если start одинаковый — по stop
            if (this.start != o.start) {
                return Integer.compare(this.start, o.start);
            } else {
                return Integer.compare(this.stop, o.stop);
            }
        }
    }
}
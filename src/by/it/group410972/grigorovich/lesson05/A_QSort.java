package by.it.group410972.grigorovich.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class A_QSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_QSort.class.getResourceAsStream("dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result = instance.getAccessory(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        // Читаем количество отрезков и точек
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int stop = scanner.nextInt();
            if (start > stop) {
                int temp = start;
                start = stop;
                stop = temp;
            }
            segments[i] = new Segment(start, stop);
        }

        Point[] points = new Point[m];
        for (int i = 0; i < m; i++) {
            int coord = scanner.nextInt();
            points[i] = new Point(coord, i);
        }

        // Создадим массив событий (сегменты - начала и концы, и точки)
        Event[] events = new Event[2 * n + m];
        int idx = 0;

        // Добавим начала отрезков (тип = 0)
        for (Segment s : segments) {
            events[idx++] = new Event(s.start, 0, -1);
        }
        // Добавим концы отрезков (тип = 2)
        for (Segment s : segments) {
            events[idx++] = new Event(s.stop, 2, -1);
        }
        // Добавим точки (тип = 1)
        for (Point p : points) {
            events[idx++] = new Event(p.coord, 1, p.index);
        }

        // Отсортируем события по времени,
        // при равенстве: начало отрезка (0) < точка (1) < конец отрезка (2)
        Arrays.sort(events);

        int count = 0;          // Сколько отрезков активно в текущий момент
        int[] result = new int[m];

        // Проходим по всем событиям в отсортированном порядке
        for (Event e : events) {
            if (e.type == 0) {          // Начало отрезка
                count++;
            } else if (e.type == 2) {   // Конец отрезка
                count--;
            } else {                    // Точка
                result[e.pointIndex] = count;
            }
        }

        return result;
    }

    // Класс отрезка
    private static class Segment {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }
    }

    // Класс точки с индексом для результата
    private static class Point {
        int coord;
        int index;

        Point(int coord, int index) {
            this.coord = coord;
            this.index = index;
        }
    }

    // Класс события: либо начало отрезка, либо конец, либо точка
    private static class Event implements Comparable<Event> {
        int coord;      // Координата события (время)
        int type;       // 0 = начало отрезка, 1 = точка, 2 = конец отрезка
        int pointIndex; // Индекс точки (для точек), для отрезков -1

        Event(int coord, int type, int pointIndex) {
            this.coord = coord;
            this.type = type;
            this.pointIndex = pointIndex;
        }

        @Override
        public int compareTo(Event other) {
            if (this.coord != other.coord) {
                return Integer.compare(this.coord, other.coord);
            }
            // При равенстве координат сортируем по типу: start < point < end
            return Integer.compare(this.type, other.type);
        }
    }
}
package by.it.group410972.grigorovich.lesson03;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class B_Huffman {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = B_Huffman.class.getResourceAsStream("dataB.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(inputStream);
        System.out.println(result);
    }

    String decode(InputStream inputStream) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);

        int k = scanner.nextInt(); // количество символов
        int l = scanner.nextInt(); // длина закодированной строки
        scanner.nextLine(); // переход на следующую строку

        // Читаем коды символов
        Map<String, Character> codeToChar = new HashMap<>();
        for (int i = 0; i < k; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(": ");
            char character = parts[0].charAt(0);
            String code = parts[1];
            codeToChar.put(code, character);
        }

        // Считываем закодированную строку
        String encoded = scanner.nextLine();

        // Декодируем по префиксному коду
        StringBuilder currentCode = new StringBuilder();
        for (char bit : encoded.toCharArray()) {
            currentCode.append(bit);
            if (codeToChar.containsKey(currentCode.toString())) {
                result.append(codeToChar.get(currentCode.toString()));
                currentCode.setLength(0); // сбрасываем временный код
            }
        }

        return result.toString();
    }
}
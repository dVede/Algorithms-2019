package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Files.write;

public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public  void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     * R = O(Nlog2N)
     * T = O(n)
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        List<Double> input = readAllLines(Paths.get(inputName)).stream()
                .map(Double::parseDouble).sorted().collect(Collectors.toList());
        write(Paths.get(outputName), Collections.singletonList(input.stream()
                .map(String::valueOf).collect(Collectors.joining("\n"))));
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     *
     * Трудоёмкость O(n)
     * Ресурсоёмкость O(n)
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        Map<String, Integer> numbers = new HashMap<>();
        int maxCount = 0;
        String maxNumber = "";
        List<String> input = readAllLines(Paths.get(inputName));
        input.forEach(i -> numbers.merge(i, 1, (a, b) -> a + b));
        for (Map.Entry<String, Integer> compareNumbers : numbers.entrySet())
            if (maxCount < compareNumbers.getValue()) {
                maxCount = compareNumbers.getValue();
                if (compareNumbers.getValue() < Integer.MAX_VALUE) maxNumber = compareNumbers.getKey();
            }
        List<String> output;
        String finalMaxNumber = maxNumber;
        output = input.stream().filter(i -> !finalMaxNumber.equals(i)).collect(Collectors.toList());
        for (int i = 0; i < maxCount; i++) output.add(maxNumber);
        write(Paths.get(outputName), output);
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     *
     * Трудоемкость: T = O(n), n  = second.length
     * Ресурсоемкость: R = O(1)
     *
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        int firstPointer = 0;
        int firstLength = first.length;
        int secondLength = second.length;
        for (int i = 0; i < secondLength; ++i) {
            if (firstPointer == first.length) continue;
            if (firstLength == secondLength) {
                second[i] = first[firstPointer++];
                continue;
            }
            second[i] = first[firstPointer].compareTo(second[firstLength]) < 0 ? first[firstPointer++] : second[firstLength++];
        }
        Arrays.stream(second).forEach(System.out::println);
    }
}

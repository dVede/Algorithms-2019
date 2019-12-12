package lesson6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
            StringBuilder res = new StringBuilder();
            int length1 = first.length();
            int length2 = second.length();
            int[][] max = new int[length1 + 1][length2 + 1];
            for (int i = 1; i < length1; i++)
                for (int j = 1; j < length2; j++)
                    max[i + 1][j + 1] = first.charAt(i) == second.charAt(j) ? 1 + max[i][j] : max(max[i + 1][j], max[i][j + 1]);
            int i = length1;
            int j = length2;
            while (i > 0 && j > 0)
                if (first.charAt(i - 1) != second.charAt(j - 1))
                    if (max[i][j] == max[i - 1][j])
                        i--;
                    else
                        j--;
                else {
                res.insert(0, first.charAt(i - 1));
                i--;
                j--;
            }
        return res.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.isEmpty())
            return Collections.emptyList();
        int[] start = new int[list.size()];
        int[] max = new int[list.size()];
        int pos = 0;
        int length = max[0];
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            max[i] = 1;
            start[i] = -1;
            for (int k = 0; k < i; k++)
                if (list.get(k) < list.get(i) && max[k] + 1 > max[i]) {
                    max[i] = max[k] + 1;
                    start[i] = k;
                }
        }
        for (int i = 0; i < max.length; i++) {
            if (max[i] > length) {
                pos = i;
                length = max[i];
            }
        }
        while (pos != -1) {
            result.add(list.get(pos));
            pos = start[pos];
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputName));
        int height = lines.size();
        int width = lines.get(0).split("\\s+").length;
        int[][] field = intsList(lines).toArray(new int[0][]);
        IntStream.range(1, height).forEach(i -> field[i][0] = field[i - 1][0] + field[i][0]);
        IntStream.range(1, width).forEach(i -> field[0][i] = field[0][i - 1] + field[0][i]);
        for (int y = 1; y < height; y++)
            for (int x = 1; x < width; x++) {
                int minimal = min(min(field[y - 1][x], field[y][x - 1]), field[y - 1][x - 1]);
                field[y][x] += minimal;
            }
        return field[height - 1][width - 1];
    }

    private static ArrayList<int[]> intsList(List<String> lines){
        ArrayList<int[]> list = new ArrayList<>();
        for (String line : lines) {
            int[] ints = new int[10];
            int count = 0;
            String[] split = line.split("\\s+");
            for (String s : split) {
                int i = Integer.parseInt(s);
                if (ints.length == count)
                    ints = Arrays.copyOf(ints, count * 2);
                ints[count++] = i;
            }
            ints = Arrays.copyOfRange(ints, 0, count);
            list.add(ints);
        }
        return list;
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}

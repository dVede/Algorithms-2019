package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.util.Set;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    //Ресурсоемкость = O(1)
    //Трудоемкость = O(N)
    static public int josephTask(int menNumber, int choiceInterval) {
        if (menNumber == 1)
            return 1;
        int result = 0;
        for (int i = 1; i <= menNumber; ++i)
            result = (result + choiceInterval) % i;
        return result + 1;
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    //Ресурсоемкость = O(n * m)
    //Трудоемкость = O(n * m)
    static public String longestCommonSubstring(String first, String second) {
        int maxLength = 0;
        int index = 0;
        int[][] words = new int[first.length()][second.length()];
        for (int i = 0; i < first.length(); i++)
            for (int j = 0; j < second.length(); j++)
                if (first.charAt(i) == second.charAt(j)) {
                    words[i][j]++;
                    if (i != 0 && j != 0) {
                        words[i][j] += words[i - 1][j - 1];
                        if (words[i][j] > maxLength) {
                            maxLength = words[i][j];
                            index = i + 1;
                        }
                    }
                }
        return first.substring(index - maxLength, index);
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    //Ресурсоемкость = O(n)
    //Трудоемкость = O(nlog(log(n)))
    static public int calcPrimesNumber(int limit) {
        if (limit <= 1) return 0;
        boolean[] isPrime = new boolean[limit + 1];
        IntStream.range(2, limit + 1).forEach(i -> isPrime[i] = true);
        for (int i = 2; i < isPrime.length; ++i)
            if (isPrime[i]) {
                for (int j = 2; i * j < isPrime.length; ++j) {
                    isPrime[i * j] = false;
                }
            }
            System.out.println(IntStream.range(2, limit + 1).filter(i -> isPrime[i]).count());
        return (int) IntStream.range(2, limit + 1).filter(i -> isPrime[i]).count();
    }
    //    for (int i = 3; i < limit + 1; i = i + 2) {
    //        if (isPrime(i)) {
    //            count++;
    //        }
    //    }
    //    return (int) count;
    //}
    //private static boolean isPrime(int n) {
    //    if (n % 2 == 0)
    //        return false;
    //    for (int i = 3; i <= sqrt(n); i = i + 2)
    //        if ((n % i) == 0)
    //            return false;
    //    return true;

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}

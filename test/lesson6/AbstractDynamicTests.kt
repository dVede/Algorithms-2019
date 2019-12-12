package lesson6

import kotlin.test.assertEquals

abstract class AbstractDynamicTests {
    fun longestCommonSubSequence(longestCommonSubSequence: (String, String) -> String) {
        assertEquals("", longestCommonSubSequence("мой мир", "я"))
        assertEquals("1", longestCommonSubSequence("1", "1"))
        assertEquals("13", longestCommonSubSequence("123", "13"))
        assertEquals("здс", longestCommonSubSequence("здравствуй мир", "мы здесь"))
        assertEquals("emt ole", longestCommonSubSequence("nematode knowledge", "empty bottle"))
        assertEquals(
            "олеет приообл е слчае прио о", longestCommonSubSequence(
                "лоупотребление не отменяет употребления; Злоупотребление не исключает правильного употребления",
                " Право следует приспособлять к тем случаям, которые происходят часто."
            )
        )
        val expectedLength = "e kerwelkkd r".length
        assertEquals(
            expectedLength, longestCommonSubSequence(
                "oiweijgw kejrhwejelkrw kjhdkfjs hrk",
                "perhkhk lerkerorwetp lkjklvvd durltr"
            ).length, "Answer must have length of $expectedLength, e.g. 'e kerwelkkd r' or 'erhlkrw kjk r'"
        )
        val expectedLength2 = """ дд саы чтых,
евшнео ваа се сви дн.
        """.trimIndent().length
        assertEquals(
            expectedLength2, longestCommonSubSequence(
                """
Мой дядя самых честных правил,
Когда не в шутку занемог,
Он уважать себя заставил
И лучше выдумать не мог.
                """.trimIndent(),
                """
Так думал молодой повеса,
Летя в пыли на почтовых,
Всевышней волею Зевеса
Наследник всех своих родных.
                """.trimIndent()
            ).length, "Answer must have length of $expectedLength2"
        )
        val expectedLength3 = """т Кумпельбаков ега""".trimIndent().length
        assertEquals(
            expectedLength3, longestCommonSubSequence(
                """Тут Кумпельбаков сделал глазом""".trimIndent(),
                """Вот Кумпельбаков пробегает""".trimIndent()
            ).length, "Answer must have length of $expectedLength2"
        )
    }

    fun longestIncreasingSubSequence(longestIncreasingSubSequence: (List<Int>) -> List<Int>) {
        assertEquals(listOf(), longestIncreasingSubSequence(listOf()))
        assertEquals(listOf(1), longestIncreasingSubSequence(listOf(1)))
        assertEquals(listOf(1, 2), longestIncreasingSubSequence(listOf(1, 2)))
        assertEquals(listOf(2), longestIncreasingSubSequence(listOf(2, 1)))
        assertEquals(
            listOf(4, 17, 18, 19, 23, 32, 47, 51, 63, 67, 74, 76, 78, 85, 88, 99),
            longestIncreasingSubSequence(
                listOf(
                    4, 41, 1, 96, 72, 65, 25, 85, 85, 30, 97, 71, 99, 44, 19, 97, 45, 17, 87, 28, 65, 78, 4, 18, 50,
                    81, 68, 19, 95, 82, 13, 98, 23, 13, 94, 94, 78, 18, 78, 62, 48, 75, 32, 47, 19, 51, 44, 63, 67, 31,
                    90, 32, 8, 94, 50, 57, 74, 17, 76, 69, 99, 88, 67, 21, 100, 60, 15, 78, 78, 93, 39, 25, 67, 71, 72,
                    85, 22, 15, 48, 88, 46, 38, 20, 53, 31, 69, 10, 5, 86, 85, 73, 84, 72, 40,
                    5, 72, 99, 20, 49, 77
                )
            )
        )
        assertEquals(
            listOf(2, 4, 8, 9, 12, 15, 18, 156, 166, 168, 169), longestIncreasingSubSequence(
                listOf(
                    2, 4, 8, 16, 32, 3, 6, 9, 12, 15, 18, 256, 12, 166, 187, 156, 166, 168, 169, 37,
                    33, 35
                )
            )
        )
        assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            longestIncreasingSubSequence(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        )
        assertEquals(listOf(2, 8, 9, 12), longestIncreasingSubSequence(listOf(2, 8, 5, 9, 12, 6)))
        assertEquals(
            listOf(23, 34, 56, 87, 91, 98, 140, 349), longestIncreasingSubSequence(
                listOf(
                    23, 76, 34, 93, 123, 21, 56, 87, 91, 12, 45, 98, 140, 12, 5, 38, 349, 65, 94,
                    45, 76, 15, 99, 100, 88, 84, 35, 88
                )
            )
        )
    }

    fun shortestPathOnField(shortestPathOnField: (String) -> Int) {
        assertEquals(1, shortestPathOnField("input/field_in2.txt"))
        assertEquals(12, shortestPathOnField("input/field_in1.txt"))
        assertEquals(43, shortestPathOnField("input/field_in3.txt"))
        assertEquals(28, shortestPathOnField("input/field_in4.txt"))
        assertEquals(222, shortestPathOnField("input/field_in5.txt"))
        assertEquals(15, shortestPathOnField("input/field_in6.txt"))
        assertEquals(22, shortestPathOnField("input/field_in7.txt"))
        assertEquals(28, shortestPathOnField("input/field_in8.txt"))
    }

}
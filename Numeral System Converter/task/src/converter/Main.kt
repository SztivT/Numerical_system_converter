package converter

import java.util.*
import kotlin.math.pow

val scanner = Scanner(System.`in`)
fun main() {
    print(convertDec(scanner.nextInt(), scanner.nextInt()))
}

fun convertDec(num: Int, base: Int): String {
    var retString = when (base) {
        2 -> "0b"
        8 -> "0"
        16 -> "0x"
        else -> ""
    }
    var remainder = num
    var power = 0
    do {
        power++
    } while (remainder.toDouble() > base.toDouble().pow(power))
    if (remainder == base.toDouble().pow(power).toInt()) {
        retString += "1"
        for (p in 0 until power) {
            retString += "0"
        }
        return retString
    }
    for (p in power - 1 downTo 0) {
        val powered = base.toDouble().pow(p).toInt()
        val times = remainder / powered
        remainder -= powered * times
        retString += numToSign(times)
    }
    return retString
}

fun numToSign(num: Int): String {
    return when (num) {
        1 -> "1"
        2 -> "2"
        3 -> "3"
        4 -> "4"
        5 -> "5"
        6 -> "6"
        7 -> "7"
        8 -> "8"
        9 -> "9"
        10 -> "a"
        11 -> "b"
        12 -> "c"
        13 -> "d"
        14 -> "e"
        15 -> "f"
        else -> "0"
    }
}

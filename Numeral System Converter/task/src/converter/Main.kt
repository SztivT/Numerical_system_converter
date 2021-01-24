package converter

import java.util.*
import kotlin.math.pow

val scanner = Scanner(System.`in`)
fun main() {
    val source = scanner.nextInt()
    val num = scanner.next()
    val target = scanner.nextInt()
    println(convertDouble(num, target, source))
}

fun convertDouble(num: String, target: Int, source: Int): String {
    var retString = ""
    val lNumber = num.split('.')
    //Integer Part
    var intPart = 0
    for (i in lNumber[0].indices) {
        val times = signToNum(lNumber[0][i])
        val value = source.toDouble().pow(lNumber[0].lastIndex - i)
        intPart += (times * value).toInt()
    }
    //if the conversion target is 1 than on every power we have one so in this case we use iteration to feel up the
    // string with ones
    if (target == 1) {
        for (i in 1..intPart) {
            retString += "1"
        }
        return retString
    }
    //Getting maximum power
    var power = 0
    do {
        power++
    } while (intPart.toDouble() > target.toDouble().pow(power))
    //If the maximum power makes the equation
    if (intPart == target.toDouble().pow(power).toInt()) {
        retString += "1"
        for (p in 0 until power) {
            retString += "0"
        }
        return retString
    }
    //If the target on maximum power is more than the integer part
    for (p in power - 1 downTo 0) {
        val powered = target.toDouble().pow(p).toInt()
        val times = intPart / powered
        intPart -= powered * times
        retString += numToSign(times)
    }
    //Fractional Part
    if (lNumber.size == 2) {
        var fractional = 0.0
        for (i in lNumber[1].indices) {
            val divident = signToNum(lNumber[1][i])
            val divisor = source.toDouble().pow(i + 1)
            fractional += divident / divisor
        }
            retString += '.'
            var remainder = fractional
            do {
                val parts = (remainder * target).toString().split('.')
                //just a break out round down
                if (parts[0].toInt() == 0) return retString
                retString += numToSign(parts[0].toInt())
                remainder = ("0.${parts[1]}").toDouble()
            } while (remainder != 0.0)
    }
    return retString
}


fun numToSign(num: Int): Char {
    return when (num) {
        in 1..9 -> (num + 48).toChar()
        in 10..35 -> (num + 87).toChar()
        else -> 48.toChar()
    }
}

fun signToNum(c: Char): Int {
    return when (c.toInt()) {
        in 49..57 -> c.toInt() - 48
        in 97..122 -> c.toInt() - 87
        else -> 0
    }
}


//For this stage the prefix definition is commented out
/*var retString = when (base) {
    2 -> "0b"
    8 -> "0"
    16 -> "0x"
    else -> ""
}
 */
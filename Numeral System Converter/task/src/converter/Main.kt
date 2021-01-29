package converter

import java.util.*
import kotlin.math.pow

val scanner = Scanner(System.`in`)
fun main() {

    try {
        val input = arrayOf("", "", "")
        var i = 0
        while (scanner.hasNext()) {
            input[i] = scanner.nextLine()
            if (i == 0) input[0] = radixValidator(input[0])
            if (i == 1) input[1] = numValidator(input[1], input[0].toInt())
            if (i == 2) input[2] = radixValidator(input[2])
            if (i == 2) break
            i++
        }
        println(convert(input))
    } catch (e: Exception) {
        println(e.message)
    }
}

fun numValidator(num: String, radix: Int): String {
    var dotted = false
    if (radix == 1) {
        for (y in num) {
            if (y.toInt() != 49) {
                if (!dotted && y == '.') {
                    dotted = true
                    continue
                }
            }
        }
        return num
    }
    for (y in num) {
        if (signToNum(y) !in 0 until radix) {
            if (!dotted && y == '.') {
                dotted = true
                continue
            }
            throw Exception("Numstring error")
        }
    }
    return num
}

fun radixValidator(radix: String): String {
    for (y in radix) {
        if (y.toInt() !in 48..57) {
            throw Exception("radix error")
        }
    }
    if (radix.toInt() !in 1..36) {
        throw Exception("radix error")
    }
    return radix
}

fun convert(input: Array<String>): String {
    var retstring = ""
    //1. split the input for integer and factorial parts
    val lNumber = input[1].split('.')
    //2. Dealing with the integer part
    var intPart = 0
    //2.1 Using Decimal system as hub
    //2.1.1 max power in the integer part of the source
    for (p in lNumber[0].lastIndex downTo 0) {
        intPart += signToNum(lNumber[0][lNumber[0].lastIndex - p]) * input[0].toDouble().pow(p).toInt()
    }
//2.2 converting the decimal hub to target
    when {
        //2.2.0 the 0 case
        intPart == 0 -> retstring += "0"
        //2.2.1 dealing with target radix = 1 case
        input[2].toInt() == 1 -> {
            for (y in 1..intPart) {
                retstring += "1"
            }
        }
        //2.2.2 dealing with hub < target
        input[2].toInt() > intPart -> retstring += numToSign(intPart).toString()
        else -> {
            //2.2.3 take the power
            var power = 1
            while (intPart > input[2].toDouble().pow(power)) {
                power++
            }
            //2.2.4 hub = target.pow(power)
            if (intPart.toDouble() == input[2].toDouble().pow(power)) {
                retstring += "1"
                for (p in power downTo 1) retstring += "0"
                return retstring
            }
            //2.2.5 from hub to target
            for (p in power - 1 downTo 0) {
                val times = intPart / input[2].toDouble().pow(p).toInt()
                retstring += numToSign(times)
                intPart -= times * input[2].toDouble().pow(p).toInt()
            }
        }
    }
//3. Dealing with the factorial part
    if (lNumber.size == 1) return retstring
    var fractional = 0.0
    for (i in lNumber[1].indices) {
        val divident = signToNum(lNumber[1][i])
        val divisor = input[0].toDouble().pow(i + 1)
        fractional += divident / divisor
    }
    if (fractional == 0.0) return retstring
    retstring += "."
    var remainder = fractional
    repeat(5) {
        //splitting the fractional part
        val fract = (remainder * input[2].toDouble()).toString().split('.')
        retstring += numToSign(fract[0].toInt())
        remainder = ("0.${fract[1]}").toDouble()
    }
    return retstring
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
        in 48..57 -> c.toInt() - 48
        in 65..90 -> c.toInt() - 55
        in 97..122 -> c.toInt() - 87
        else -> -1
    }
}
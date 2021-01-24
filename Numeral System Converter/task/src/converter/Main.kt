package converter

import java.util.*
import kotlin.math.pow

val scanner = Scanner(System.`in`)
fun main() {
    // write your code here
    print(baseEight(scanner.nextInt()))
}

fun binary(num: Int): String {
    var remainder = num
    var binary = "0b"
    var pow = 0
    do {
        pow++
    } while (remainder > 2.0.pow(pow))
    for (i in 0..pow) {
        if (remainder == 1) {
            binary += 1
            break
        }
        val divider = 2.0.pow(pow - i).toInt()
        if (remainder >= divider) {
            binary += "1"
            remainder -= divider
        } else {
            binary += 0
        }
    }
    return binary
}

fun baseEight(num: Int): Int {
    return num % 8
}
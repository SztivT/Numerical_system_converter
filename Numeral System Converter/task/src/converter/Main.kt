package converter

import kotlin.math.pow

fun main() {
    // write your code here
    val decimal = 4
    print("$decimal = ${binary(decimal)}")
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
        }else{
            binary += 0
        }
    }
    return binary
}

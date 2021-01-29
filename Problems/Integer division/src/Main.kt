fun intDivision(x: String, y: String): Int {
    var quotient = 0
    try {
        val nX = stringToNum(x)
        val nY = stringToNum(y)
        quotient += divide(nX, nY)

    } catch (e: Exception) {
        println(e.message)
    }
    return quotient
}

fun main() {
    val x = readLine()!!
    val y = readLine()!!
    println(intDivision(x, y))
}

fun divide(divident: Int, divisor: Int): Int {
    if (divisor == 0) {
        throw Exception("Exception: division by zero!")
    }
    return divident / divisor
}

fun stringToNum(input: String): Int {
    var x = 0
    for (y in input) {
        if (y.toInt() !in 48..57) {
            throw Exception("Read values are not integers.")
        }
        x = input.toInt()
    }
    return x
}
import java.util.*

fun main() {
    // put your code here
    val scanner = Scanner(System.`in`)
    val divident = scanner.nextInt()
    val divisor = scanner.nextInt()
    safeDivision(divident, divisor)
}

fun safeDivision(divident: Int, divisor: Int): Int {
    val quotient: Int
    try {
        if (divisor == 0) {
            throw Exception("Division by Zero is impossible")
        }
    }
    catch (e: Exception) {
        println(e)
    }
    finally {
        quotient = divident / divisor
    }
    return quotient
}
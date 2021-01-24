import java.util.*

fun main() {
    // put your code here
    val scanner = Scanner(System.`in`)
    val divident = scanner.nextInt()
    val divisor = scanner.nextInt()
    safeDivision(divident, divisor)
}

fun safeDivision(divident: Int, divisor: Int): Int {
    try {
        return divident / divisor
    }
    catch (ae: ArithmeticException) {
        println(ae)
    }
}
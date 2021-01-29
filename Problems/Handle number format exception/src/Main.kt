fun parseCardNumber(cardNumber: String): Long {
    val cardNumParts = cardNumber.split(' ')
    var numString = ""
    for (part in cardNumParts) {
        if (part.length != 4) {
            throw Exception("The card number contains 4 digit blocks")
        }
        for (digit in part) {
            if (digit.toInt() !in 48..57) {
                throw Exception("Wrong Card Number")
            } else {
                numString += digit
            }
        }
    }
    return numString.toLong()
}
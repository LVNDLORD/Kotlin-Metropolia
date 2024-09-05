
class FractionMutable(var numerator: Int, var denominator: Int, var sign: Int = 1) {

    init {
        require(denominator != 0) { "Denominator cannot be zero" }
        numerator = if (numerator < 0) -numerator else numerator
        denominator = if (denominator < 0) -denominator else denominator
        reduce()
    }

    fun negate() {
        this.sign *= -1
    }

    fun add(secondFraction: FractionMutable) {
        var newNumerator = 0
        val commonDenominator = denominator * secondFraction.denominator
        if (sign == secondFraction.sign) {
             newNumerator = numerator * secondFraction.denominator + secondFraction.numerator * denominator
        } else {
            newNumerator = numerator * secondFraction.denominator - secondFraction.numerator * denominator
        }
        numerator = newNumerator
        denominator = commonDenominator
        reduce()

    }

    fun mult(secondFraction: FractionMutable) {
        numerator *= secondFraction.numerator
        denominator *= secondFraction.denominator
        sign *= secondFraction.sign
        reduce()
    }

    fun div(secondFraction: FractionMutable) {
        require(secondFraction.numerator != 0) { "Cannot divide by zero" }
        numerator *= secondFraction.denominator
        denominator *= secondFraction.numerator
        reduce()
    }

    fun intPart(): Int {
        return (numerator * sign) / denominator
    }

    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }

    private fun reduce() {
        val gcd = gcd(numerator, denominator)
        numerator /= gcd
        denominator /= gcd
    }



    override fun toString(): String {
        val signPrefix = if (sign < 0) "-" else ""
        return "$signPrefix${Math.abs(numerator)}/${denominator}"
    }

}

fun main() {
    val a = FractionMutable(1, 2)
    println(a.toString()) // Output: "-1/2"

    a.mult(FractionMutable(1,3,-1))
    println(a.toString())

    a.add(FractionMutable(1, 3, -1))
    println(a.toString()) // Output: "5/6"

    val b = FractionMutable(8, 3)
    b.div(FractionMutable(4, 6))
    println(b.toString()) // Output: "48/12" (not simplified)
}

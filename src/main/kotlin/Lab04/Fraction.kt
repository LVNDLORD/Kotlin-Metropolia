package Lab04

class Fraction(
    private var numerator: Int, private var denominator: Int,
    private val sign: Int = 1
) : Comparable<Fraction> {


    init {
        require(denominator != 0) { "Denominator cannot be zero" }
        require(numerator != 0 || denominator != 0) { "Zero denominator is invalid" }
        reduce()
    }

    fun negate(): Fraction {
        val negative = sign * -1
        return Fraction(numerator, denominator, negative)

    }

    fun add(secondFraction: Fraction): Fraction {
        val commonDenominator = denominator * secondFraction.denominator
        val newNumerator =
            numerator * secondFraction.denominator * sign + secondFraction.numerator * denominator * secondFraction.sign
        val newSign = if (newNumerator < 0) -1 else 1
        reduce()

        return Fraction(kotlin.math.abs(newNumerator), commonDenominator, newSign)
    }

    fun mult(secondFraction: Fraction): Fraction {
        val newNumerator = numerator * secondFraction.numerator
        val newDenominator = denominator * secondFraction.denominator
        val newSign = sign * secondFraction.sign
        reduce()

        return Fraction(newNumerator, newDenominator, newSign)
    }

    fun div(secondFraction: Fraction): Fraction {
        require(secondFraction.numerator != 0) { "Cannot divide by zero" }
        val newNumerator = numerator * secondFraction.denominator
        val newDenominator = denominator * secondFraction.numerator
        val newSign = sign * secondFraction.sign
        reduce()

        return Fraction(newNumerator, newDenominator, newSign)
    }


    operator fun unaryMinus(): Fraction = Fraction(numerator, denominator, -sign)
    operator fun plus(other: Fraction): Fraction = this.add(other)
    operator fun times(other: Fraction): Fraction = this.mult(other)
    operator fun minus(other: Fraction): Fraction = this.add(-other)

    override fun compareTo(other: Fraction): Int {
        val thisFraction = this.numerator * other.denominator * this.sign
        val otherFraction = other.numerator * this.denominator * other.sign
        return thisFraction.compareTo(otherFraction)
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Fraction

        if (numerator != other.numerator) return false
        if (denominator != other.denominator) return false
        if (sign != other.sign) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numerator
        result = 31 * result + denominator
        result = 31 * result + sign
        return result
    }

    override fun toString(): String {
        val signPrefix = if (sign < 0) "-" else ""
        return "$signPrefix${Math.abs(numerator)}/${denominator}"
    }


}


fun main() {
    val a = Fraction(1, 2, -1)
    println(a)
    println(a.add(Fraction(1, 3)))
    println(a.mult(Fraction(5, 2, -1)))
    println(a.div(Fraction(2, 1)))
    println(-Fraction(1, 6) + Fraction(1, 2))
    println(Fraction(2, 3) * Fraction(3, 2))
    println(Fraction(1, 2) > Fraction(2, 3))  // Comparable interface function compareTo()

}
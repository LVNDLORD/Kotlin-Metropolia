package Lab03

class Lotto(
    private val lottoRange: IntRange = 1..40,
    val n: Int = 7,
) {


    // Generates a list of n distinct numbers in the given range.
    fun pickNDistinct(range: IntRange, n: Int): List<Int>? {
        return if (n > range.count()) null else range.shuffled().take(n)
    }

    // Returns the number of distinct numbers in the list.
    fun numDistinct(list: List<Int>): Int {
        return list.toSet().size
    }

    // Returns the number of common numbers between list1 and 2.
    fun numCommon(list1: List<Int>, list2: List<Int>): Int {
        return list1.intersect(list2.toSet()).size
    }

    // Checks if the guess contains n distinct numbers within the given range.
    fun isLegalLottoGuess(guess: List<Int>, range: IntRange = lottoRange, count: Int = n): Boolean {
        return guess.size == count && guess.toSet().size == count && guess.all { it in range }
    }

    // Checks how many numbers in the guess are in the secret list. Returns 0 if the guess is illegal.
    fun checkGuess(guess: List<Int>, secret: List<Int>): Int {
        return if (isLegalLottoGuess(guess)) numCommon(guess, secret) else 0
    }

    //B
    fun readNDistinct(low: Int, high: Int, n: Int): List<Int> {
        while (true) {
            println("Give $n numbers from $low to $high, separated by commas:")
            val input = readLine() ?: return emptyList()
            val numbers = input.split(",")
                .map { it.trim().toIntOrNull() }
                .filterNotNull()
                .distinct()

            if (numbers.size == n && numbers.all { it in low..high }) {
                return numbers
            }

            println("Invalid input. Please ensure you provide $n distinct numbers within the range.")
        }
    }

    fun playLotto() {
        val lotto = Lotto(lottoRange, n)
        val secretNumbers = lotto.pickNDistinct(lottoRange, n) ?: emptyList()

        while (true) {
            val guess = readNDistinct(1, 40, 7)
            val correctCount = lotto.checkGuess(guess, secretNumbers)
            println("Lotto numbers: $secretNumbers, you got $correctCount correct")
            val (steps, computerGuess) = lotto.findLotto(lotto)
            println("Computer guess in $steps steps is $computerGuess")

            print("More? (Y/N): ")
            if (readLine()?.uppercase() != "Y") break
        }
    }


    //C
    fun findLotto(lotto: Lotto): Pair<Int, List<Int>> {
        val possibleNumbers = lottoRange.toMutableList()
        val foundNumbers = mutableListOf<Int>()
        var attempts = 0

        while (foundNumbers.size < 7) {
            val currentGuess = lotto.pickNDistinct(lottoRange, 7 - foundNumbers.size)!!
            val mixedGuess = foundNumbers + currentGuess
            attempts++

            val correctCount = lotto.checkGuess(mixedGuess, mixedGuess)

            if (correctCount > foundNumbers.size) {
                val newlyFoundCount = correctCount - foundNumbers.size
                foundNumbers.addAll(currentGuess.take(newlyFoundCount))
                possibleNumbers.removeAll(foundNumbers)
            }
        }

        return Pair(attempts, foundNumbers)
    }


}

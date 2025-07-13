package com.fredprojects.helloandroid.features.inequality

/** InequalityUseCase represents the use case for solving inequality in the form of `A`x + `B` < 0 **/
class InequalityUseCase {
    /**
     * Solves the inequality of the form `A`x + `B` < 0
     * @param a the first coefficient
     * @param b the second coefficient
     * @return InequalityResult
     */
    operator fun invoke(a: Float?, b: Float?): InequalityResult = when {
        b == null || a == null -> InequalityResult(InequalityStatus.ERROR)
        (b == 0f && a == 0f) || (a == 0f && b > 0f) -> InequalityResult(InequalityStatus.NO_SOLUTION)
        a == 0f && b < 0f -> InequalityResult(InequalityStatus.FIRST_SOLUTION, "$b < 0\nx = (− ∞ , + ∞)")
        a < 0 -> InequalityResult(InequalityStatus.SECOND_SOLUTION, "x > ${-b/a}\nx = (${-b/a}, + ∞)")
        else -> InequalityResult(InequalityStatus.THIRD_SOLUTION, "x < ${-b/a}\nx = (− ∞ , ${-b/a})")
    }
}

/** InequalityResult represents the result of the InequalityUseCase execution **/
data class InequalityResult(
    val status: InequalityStatus = InequalityStatus.EMPTY,
    val solution: String = ""
)

/**
 * InequalityStatus represents the status of the inequality solution
 * @property ERROR if the input is not valid
 * @property NO_SOLUTION if there is no solution
 * @property FIRST_SOLUTION if the solution is `b < 0 x = (− ∞ , + ∞)`
 * @property SECOND_SOLUTION if the solution is `x > ${-b/a} x = (${-b/a}, + ∞)`
 * @property THIRD_SOLUTION if the solution is `x < ${-b/a} x = (− ∞ , ${-b/a})`
 */
enum class InequalityStatus {
    EMPTY,
    ERROR,
    NO_SOLUTION,
    FIRST_SOLUTION,
    SECOND_SOLUTION,
    THIRD_SOLUTION
}
package com.fredprojects.helloandroid.features.inequality.presentation

import com.fredprojects.helloandroid.features.inequality.*

internal fun InequalityResult.isSuccess(): Boolean = when(status) {
    InequalityStatus.ERROR -> false
    InequalityStatus.NO_SOLUTION -> false
    else -> true
}
internal fun InequalityResult.content(): Int = when(status) {
    InequalityStatus.ERROR -> R.string.inequalityError
    InequalityStatus.NO_SOLUTION -> R.string.inequalityHasNoSolutions
    else -> 0
}
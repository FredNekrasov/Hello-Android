package com.fredprojects.helloandroid.features.inequality.presentation.vm

import androidx.lifecycle.ViewModel
import com.fredprojects.helloandroid.features.inequality.InequalityResult
import com.fredprojects.helloandroid.features.inequality.InequalityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InequalityVM(
    private val inequalityUseCase: InequalityUseCase,
) : ViewModel() {
    private val solutionMSF = MutableStateFlow<InequalityResult>(InequalityResult())
    val solution = solutionMSF.asStateFlow()
    fun solveTheInequality(a: Float?, b: Float?) {
        solutionMSF.value = inequalityUseCase(a, b)
    }
}
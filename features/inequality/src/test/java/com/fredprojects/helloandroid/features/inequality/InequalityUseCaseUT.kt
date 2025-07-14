package com.fredprojects.helloandroid.features.inequality

import junit.framework.TestCase.assertNotSame
import junit.framework.TestCase.assertEquals
import org.junit.Test

class InequalityUseCaseUT {
    private val inequalityUseCase = InequalityUseCase()
    // success cases (SC)
    @Test
    fun statusIsErrorV1SC() {
        var result = inequalityUseCase(null, null)
        assertEquals(InequalityStatus.ERROR, result.status)
        assertEquals("", result.solution)

        result = inequalityUseCase(1f, null)
        assertEquals(InequalityStatus.ERROR, result.status)
        assertEquals("", result.solution)

        result = inequalityUseCase(null, 2f)
        assertEquals(InequalityStatus.ERROR, result.status)
        assertEquals("", result.solution)
    }
    @Test
    fun statusIsNoSolutionSC() {
        var result = inequalityUseCase(0f, 0f)
        assertEquals(InequalityStatus.NO_SOLUTION, result.status)
        assertEquals("", result.solution)

        result = inequalityUseCase(0f, 1f)
        assertEquals(InequalityStatus.NO_SOLUTION, result.status)
        assertEquals("", result.solution)
    }
    @Test
    fun statusIsFirstSolutionSC() {
        val result = inequalityUseCase(0f, -1f)
        assertEquals(InequalityStatus.FIRST_SOLUTION, result.status)
        assertEquals("-1.0 < 0\nx = (− ∞ , + ∞)", result.solution)
    }
    @Test
    fun statusIsSecondSolutionSC() {
        val result = inequalityUseCase(-1f, 0f)
        assertEquals(InequalityStatus.SECOND_SOLUTION, result.status)
        assertEquals("x > 0.0\nx = (0.0, + ∞)", result.solution)
    }
    @Test
    fun statusIsThirdSolutionSC() {
        val result = inequalityUseCase(1f, 1f)
        assertEquals(InequalityStatus.THIRD_SOLUTION, result.status)
        assertEquals("x < -1.0\nx = (− ∞ , -1.0)", result.solution)
    }
    
    // invalid cases (IC)
    @Test
    fun statusIsErrorIC() {
        val result = inequalityUseCase(0f, 0f)
        assertNotSame(InequalityStatus.ERROR, result.status)
    }
    @Test
    fun statusIsNoSolutionIC() {
        val result = inequalityUseCase(1f, 0f)
        assertNotSame(InequalityStatus.NO_SOLUTION, result.status)
    }
    @Test
    fun statusIsFirstSolutionIC() {
        val result = inequalityUseCase(1f, 1f)
        assertNotSame(InequalityStatus.FIRST_SOLUTION, result.status)
    }
    @Test
    fun statusIsSecondSolutionIC() {
        val result = inequalityUseCase(1f, 0f)
        assertNotSame(InequalityStatus.SECOND_SOLUTION, result.status)
    }
    @Test
    fun statusIsThirdSolutionIC() {
        val result = inequalityUseCase(-1f, -1f)
        assertNotSame(InequalityStatus.THIRD_SOLUTION, result.status)
    }
}
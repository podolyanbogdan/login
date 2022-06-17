package com.example.login.canvas

import com.example.login.viewmodels.CellState

data class BoardModel(
    val threef: Float = 3f,
    var matrixSize: Array<CellState> = Array(9){CellState.None},
    val rangeLine: IntRange = (0..2)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BoardModel

        if (threef != other.threef) return false
        if (!matrixSize.contentEquals(other.matrixSize)) return false
        if (rangeLine != other.rangeLine) return false

        return true
    }

    override fun hashCode(): Int {
        var result = threef.hashCode()
        result = 31 * result + matrixSize.contentHashCode()
        result = 31 * result + rangeLine.hashCode()
        return result
    }
}
package com.example.login.ui.fragments

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.R
import com.example.login.canvas.WinLineState
import com.example.login.utils.SingleLiveEvent

class GameViewModel : ViewModel() {
    private var isTurn = true
    private lateinit var matrix: Array<CellState>
    private lateinit var currentCellState: CellState

    private val mStates = MutableLiveData<Pair<GameStatus, Array<CellState>>>()
    private val mCurrentMove = MutableLiveData<CellState>()
    private val mCellStateByIndex: MutableLiveData<Pair<Int, CellState>> = SingleLiveEvent()
    private val mWinState : MutableLiveData<WinLineState> = MutableLiveData()

    val states: LiveData<Pair<GameStatus, Array<CellState>>> = mStates
    val currentMove: LiveData<CellState> = mCurrentMove
    val cellStateByIndex: LiveData<Pair<Int, CellState>> = mCellStateByIndex
    val winState: LiveData<WinLineState> = mWinState

    init {
        initGame()
    }

    private fun initGame() {
        matrix = Array(9) { CellState.None }
        currentCellState = CellState.Cross
        mWinState.value = WinLineState.None
        mStates.value = Pair(GameStatus.Started, matrix)
        mCurrentMove.value = currentCellState
    }

    private fun computerMakeTurn(){
        val compChoice = (1..9).random()
        if(matrix[compChoice] == CellState.None){
            val row = compChoice / 3
            val column = compChoice % 3
            matrix[compChoice] = currentCellState
            mCellStateByIndex.value = Pair(compChoice, currentCellState)
            val state = checkWin(row, column)
            if (state != WinLineState.None) {
                mStates.value = Pair(GameStatus.Finished, matrix)
                mWinState.value = state
                return
            }
            currentCellState = if (currentCellState == CellState.Cross) CellState.Circle else CellState.Cross
            mCurrentMove.value = currentCellState
        } else {
            computerMakeTurn()
        }
    }

    fun onCellClick(index: Int) {
        val row = index / 3
        val column = index % 3
        matrix[index] = currentCellState
        mCellStateByIndex.value = Pair(index, currentCellState)

        val state = checkWin(row, column)
        if (state != WinLineState.None) {
            mStates.value = Pair(GameStatus.Finished, matrix)
            mWinState.value = state
            return
        }

        currentCellState = if (currentCellState == CellState.Cross) CellState.Circle else CellState.Cross
        mCurrentMove.value = currentCellState
        isTurn = true
        computerMakeTurn()
    }

    private fun checkWin(row: Int, column: Int): WinLineState {
        //ряд
        if (checkLine { matrix[getIndex(row, it)] == currentCellState }) return WinLineState.Horizontal(row)
        // колонка
        if (checkLine { matrix[getIndex(it, column)] == currentCellState }) return WinLineState.Vertical(column)
        // диагональ
        if (row == column) { if (checkLine { matrix[getIndex(it, it)] == currentCellState }) return WinLineState.MainDiagonal }
        // диагональ 2
        if (row + column == 2) { if (checkLine { matrix[getIndex(it, 2 - it)] == currentCellState }) return WinLineState.ReverseDiagonal }
        return WinLineState.None
    }

    private fun checkLine(function: (Int) -> Boolean): Boolean {
        for (i in 0..2) {
            if (!function(i)) return false
        }
        return true
    }

    fun onReloadClick() {
        initGame()
    }

    private fun getIndex(row: Int, column: Int) = row * 3 + column
}

enum class CellState(@DrawableRes val icon: Int, val isClickable: Boolean) {
    None(0, true),
    Cross(R.drawable.ic_x, false),
    Circle(R.drawable.ic_o, false)
}

enum class Players()
{
    Person,
    Computer
}

enum class GameStatus {
    Started,
    Finished
}
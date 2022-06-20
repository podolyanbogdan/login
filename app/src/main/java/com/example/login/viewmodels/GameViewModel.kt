package com.example.login.viewmodels

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.R
import com.example.login.canvas.BoardModel
import com.example.login.canvas.WinLineState
import com.example.login.utils.SingleLiveEvent

const val boardSize = 9

class GameViewModel : ViewModel() {
    private val boardModel = BoardModel()
    private var isTurn = true
    private var currentClick = 0

    private lateinit var currentCellState: CellState

    private val mStates = MutableLiveData<Pair<GameStatus, Array<CellState>>>()
    private val mCurrentMove = MutableLiveData<CellState>()
    private val mCellStateByIndex: MutableLiveData<Pair<Int, CellState>> = SingleLiveEvent()
    private val mWinState: MutableLiveData<WinLineState> = MutableLiveData()

    val states: LiveData<Pair<GameStatus, Array<CellState>>> = mStates
    val currentMove: LiveData<CellState> = mCurrentMove
    val cellStateByIndex: LiveData<Pair<Int, CellState>> = mCellStateByIndex
    val winState: LiveData<WinLineState> = mWinState


    private fun initGame() {
        boardModel.matrixSize = Array(boardSize) { CellState.None }
        currentCellState = CellState.Cross
        mWinState.value = WinLineState.None
        mStates.value = Pair(GameStatus.Started, boardModel.matrixSize)
        mCurrentMove.value = currentCellState
        currentClick = 0
    }

    init {
        initGame()
    }


    fun onCellClick(index: Int) {
        if (isTurn && currentClick != 9) {
            val row = index / 3
            val column = index % 3
            currentCellState = CellState.Cross
            boardModel.matrixSize[index] = currentCellState
            mCellStateByIndex.value = Pair(index, currentCellState)

            val state = checkWin(row, column)
            if (state != WinLineState.None) {
                mStates.value = Pair(GameStatus.Finished, boardModel.matrixSize)
                mWinState.value = state
                return
            }
            mCurrentMove.value = currentCellState
            isTurn = false
            currentClick++
        }

        compOnCellClick()
    }

    fun playerPlayerGame(index: Int) {
        val row = index / 3
        val column = index % 3
        boardModel.matrixSize[index] = currentCellState
        mCellStateByIndex.value = Pair(index, currentCellState)

        val state = checkWin(row, column)
        if (state != WinLineState.None) {
            mStates.value = Pair(GameStatus.Finished, boardModel.matrixSize)
            mWinState.value = state
            return
        }
        currentCellState =
            if (currentCellState == CellState.Cross) CellState.Circle else CellState.Cross
        currentClick++
    }

    fun compOnCellClick() {
        isTurn = false
        if (!isTurn && currentClick != 9) {
            val compChoice = (1..8).random()
            if (boardModel.matrixSize[compChoice] == CellState.None) {
                currentCellState = CellState.Circle
                boardModel.matrixSize[compChoice] = currentCellState
                val row = compChoice / 3
                val column = compChoice % 3
                mCellStateByIndex.value = Pair(compChoice, currentCellState)
                val state = checkWin(row, column)
                if (state != WinLineState.None) {
                    mStates.value = Pair(GameStatus.Finished, boardModel.matrixSize)
                    mWinState.value = state
                    return
                }
                mCurrentMove.value = currentCellState
                isTurn = true
                currentClick++
            } else {
                compOnCellClick()
            }
        }
    }

    private fun checkWin(row: Int, column: Int): WinLineState {
        //ряд
        if (checkLine {
                boardModel.matrixSize[getIndex(
                    row,
                    it
                )] == currentCellState
            }) return WinLineState.Horizontal(row)
        // колонка
        if (checkLine {
                boardModel.matrixSize[getIndex(
                    it,
                    column
                )] == currentCellState
            }) return WinLineState.Vertical(column)
        // диагональ
        if (row == column) {
            if (checkLine {
                    boardModel.matrixSize[getIndex(
                        it,
                        it
                    )] == currentCellState
                }) return WinLineState.MainDiagonal
        }
        // диагональ 2
        if (row + column == 2) {
            if (checkLine {
                    boardModel.matrixSize[getIndex(
                        it,
                        2 - it
                    )] == currentCellState
                }) return WinLineState.ReverseDiagonal
        }
        if(currentClick == 8){
            onReloadClick()
        }
        return WinLineState.None
    }

    private fun checkLine(function: (Int) -> Boolean): Boolean {
        for (i in boardModel.rangeLine) {
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

enum class GameStatus {
    Started,
    Finished
}
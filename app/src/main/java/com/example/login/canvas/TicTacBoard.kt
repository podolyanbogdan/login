package com.example.login.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.widget.GridLayout
import androidx.core.content.res.ResourcesCompat
import com.example.login.R

class TicTacBoard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GridLayout(context, attrs, defStyleAttr) {
    val boardModel = BoardModel()

    private var mWinLineState: WinLineState = WinLineState.None
    private var winLineRect: RectF? = null

    private var widthF: Float = 0f
    private var heightF: Float = 0f
    private var cellWidth: Float = 0f
        set(value) {
            field = value
            halfCellWidth = value / 2
        }

    private var cellHeight: Float = 0f
        set(value) {
            field = value
            halfCellHeight = value / 2
        }
    private var halfCellWidth: Float = 0f

    private var halfCellHeight: Float = 0f
    private val strokeSize = resources.getDimension(R.dimen.stroke_size)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ResourcesCompat.getColor(context.resources, R.color.item_color, null)
        strokeWidth = strokeSize
        strokeCap = Paint.Cap.ROUND
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        widthF = MeasureSpec.getSize(widthSpec).toFloat()
        heightF = MeasureSpec.getSize(heightSpec).toFloat()

        cellWidth = widthF / boardModel.threef
        cellHeight = heightF / boardModel.threef

        drawWinLine(mWinLineState)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        canvas?.run {
            for (i in 1..2) {
                drawLine(cellWidth * i, strokeSize, cellWidth * i, heightF - strokeSize, paint)
                drawLine(strokeSize, cellHeight * i, widthF - strokeSize, cellHeight * i, paint)
            }
            winLineRect?.let {
                val line: RectF = when (mWinLineState) {
                    is WinLineState.Horizontal ->
                        RectF(it.left, it.top, it.right, it.bottom)
                    is WinLineState.Vertical ->
                        RectF(it.left, it.top, it.right, it.bottom)
                    WinLineState.MainDiagonal ->
                        RectF(it.left, it.top, it.right, it.bottom)
                    WinLineState.ReverseDiagonal ->
                        RectF(it.left, it.top, it.right, it.bottom)
                    WinLineState.None -> it
                }
                drawLine(line.left, line.top, line.right, line.bottom, paint)
            }
        }
    }

    fun drawWinLine(winLineState: WinLineState) {
        mWinLineState = winLineState
        winLineRect = when (winLineState) {
            WinLineState.MainDiagonal ->
                RectF(strokeSize, strokeSize, widthF - strokeSize, heightF - strokeSize)
            WinLineState.ReverseDiagonal ->
                RectF(widthF - strokeSize, strokeSize, strokeSize, heightF - strokeSize)
            is WinLineState.Horizontal -> {
                val y = halfCellHeight + cellHeight * winLineState.row
                RectF(strokeSize, y, widthF - strokeSize, y)
            }
            is WinLineState.Vertical -> {
                val x = halfCellWidth + cellWidth * winLineState.column
                RectF(x, strokeSize, x, heightF - strokeSize)
            }
            WinLineState.None -> null
        }
    }

}

sealed class WinLineState {
    data class Horizontal(val row: Int) : WinLineState()
    data class Vertical(val column: Int) : WinLineState()
    object MainDiagonal : WinLineState()
    object ReverseDiagonal : WinLineState()
    object None : WinLineState()
}
package com.example.login.repository

import android.content.Context
import com.example.login.R

class ScenarioRepository constructor(private val context: Context) {
    val sceneLectureHall = mutableListOf(
        context.getString(R.string.lecture1),
        context.getString(R.string.lecture2),
        context.getString(R.string.lecture3),
        context.getString(R.string.lecture4),
        context.getString(R.string.lecture5),
        context.getString(R.string.lecture6),
        context.getString(R.string.lecture7),
        context.getString(R.string.lecture8),
        context.getString(R.string.lecture9),
    )

    fun getSceneLectureHall(index: Int): String {
        return sceneLectureHall[index]
    }

    val sceneRightAway = mutableListOf(
        context.getString(R.string.right1),
        context.getString(R.string.right2),
        context.getString(R.string.right3),
        context.getString(R.string.right4),
        context.getString(R.string.right5),
        context.getString(R.string.right6),
        context.getString(R.string.right7),
        context.getString(R.string.right8),
        context.getString(R.string.right9),
        context.getString(R.string.right10),
        context.getString(R.string.right11),
        context.getString(R.string.right12),
        context.getString(R.string.right13),
        context.getString(R.string.right14),
        context.getString(R.string.right15),
        context.getString(R.string.right16),
    )

    fun getSceneRightAway(index: Int): String {
        return sceneRightAway[index]
    }

    val sceneGame = mutableListOf(
        context.getString(R.string.game1),
        context.getString(R.string.game2),
        context.getString(R.string.game3),
        context.getString(R.string.game4),
        context.getString(R.string.game5),
        context.getString(R.string.game6),
        context.getString(R.string.game7),
        context.getString(R.string.game8),
        context.getString(R.string.game9)
    )

    fun getSceneGame(index: Int): String {
        return sceneGame[index]
    }

    val sceneBook = mutableListOf(
        context.getString(R.string.book1),
        context.getString(R.string.book2),
        context.getString(R.string.book3),
        context.getString(R.string.book4),
        context.getString(R.string.book5),
        context.getString(R.string.book6),
        context.getString(R.string.book7),
        context.getString(R.string.book8)
    )

    fun getSceneBook(index: Int): String {
        return sceneBook[index]
    }

    val sceneMarry = mutableListOf(
        context.getString(R.string.marry1),
        context.getString(R.string.marry2),
        context.getString(R.string.marry3),
        context.getString(R.string.marry4),
        context.getString(R.string.marry5),
        context.getString(R.string.marry6),
        context.getString(R.string.marry7),
        context.getString(R.string.marry8),
        context.getString(R.string.marry9),
        context.getString(R.string.marry10),
        context.getString(R.string.marry11),
        context.getString(R.string.marry12),
        context.getString(R.string.marry13),
        context.getString(R.string.marry14),
        context.getString(R.string.marry15),
        context.getString(R.string.marry17),
        context.getString(R.string.marry18),
        context.getString(R.string.marry19),
        context.getString(R.string.marry20),
        context.getString(R.string.marry21),
        context.getString(R.string.marry22),
        context.getString(R.string.marry23),
        context.getString(R.string.marry24),
        context.getString(R.string.marry25),
    )

    fun getSceneMarry(index: Int): String {
        return sceneMarry[index]
    }

    val sceneBadEnding = mutableListOf(
        context.getString(R.string.badEnd1),
        context.getString(R.string.badEnd2),
        context.getString(R.string.badEnd3),
        context.getString(R.string.badEnd4),
        context.getString(R.string.badEnd5)
    )

    fun getSceneBadEnding(index: Int): String {
        return sceneBadEnding[index]
    }
}
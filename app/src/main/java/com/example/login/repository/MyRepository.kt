package com.example.login.repository

object MyRepository {
    var level = 0
    fun getLevel(level: Int){
        this.level = level
    }
    fun setLevel(): Int {
        return level
    }

    var character = ""
    fun getCharacter(character: String){
        this.character = character
    }
    fun setCharacter(): String {
        return character
    }

}
package com.example.login.repository


private var currentImagePath = ""
class ImageRepository() {
    fun saveCurrentImagePath(path: String){
        currentImagePath = path
    }
    fun takeCurrentImagePath(): String{
        return currentImagePath
    }
}
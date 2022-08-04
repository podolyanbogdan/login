package com.example.login.jsoup

import com.example.login.constants.Constants
import org.jsoup.Jsoup
import java.io.IOException

class JsoupParser {
    fun parseWebPage(): String {
        var resultJsonString = ""
        try {
            val url = Constants.URL_PAGE
            val html = Jsoup.connect(url).get()
            val pTag = html.text()
            resultJsonString = pTag
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return resultJsonString
    }
}
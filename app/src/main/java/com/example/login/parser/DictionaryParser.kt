package com.example.login.parser

import android.content.Context
import com.example.login.data.WordTranslatedModel
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import com.example.login.constants.*


fun parserDictionary(context: Context, args: String, argsLang: String): ArrayList<WordTranslatedModel> {
    val wordList = arrayListOf<WordTranslatedModel>()

    var currentTag = ""
    var resultValue: String
    var tag: String?
    var value = ""
    var xmlDict = context.assets?.open(DICT_POL)


    if(argsLang == UA_TO_CHECK)
    {
        xmlDict = context.assets?.open(DICT_UA)
    }
    if(argsLang == POL_TO_CHECK)
    {
        xmlDict = context.assets?.open(DICT_POL)
    }
    val parserFactory: XmlPullParserFactory = XmlPullParserFactory.newInstance()
    val parser: XmlPullParser = parserFactory.newPullParser()
    parser.setInput(xmlDict, null)

    var event = parser.eventType
    while (event != XmlPullParser.END_DOCUMENT) {
        tag = parser.name
        when (event) {
            XmlPullParser.TEXT -> {
                value = parser.text
                value = value
                    .replace("\"", "")
                    .replace(" ", "")
            }
            XmlPullParser.END_TAG -> when (tag) {
                "k" -> { currentTag = value }
                "ar" -> {
                    if(args == currentTag){
                        resultValue = value
                        wordList.add(WordTranslatedModel(args, resultValue))
                    }
                }
            }
        }
        event = parser.next()
    }
    return wordList
}
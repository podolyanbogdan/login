package com.example.login.data

import com.example.login.constants.Constants.IMG2_TOILET
import com.example.login.constants.Constants.IMG_KITCHEN

enum class FakeLocaleSync(val data: String) {
    FAKE_PIC1(IMG_KITCHEN),
    FAKE_PIC2(IMG2_TOILET),
    OFF("off"),
    F20("20.5F"),
    SWITCH("on"),
    SEVEN("7"),
    FIVE("5.5"),
}
package com.example.login.data.mapper

import com.example.login.retrofit.entity.AgeEntity
import com.example.login.ui.age.model.UserModel

class UserMapper : MyMapper<AgeEntity, UserModel>() {
    override fun map(from: AgeEntity): UserModel {
        return UserModel(
            age = from.age ?: 0,
            count = from.count?: 0,
            name = from.name?: ""
        )
    }
}
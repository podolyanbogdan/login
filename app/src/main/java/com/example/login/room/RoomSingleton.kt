package com.example.login.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteModel::class], version = 3, exportSchema = false)
abstract class RoomSingleton : RoomDatabase(){
    abstract fun noteDao():NoteDao

    companion object{
        private var INSTANCE: RoomSingleton? = null
        fun getInstance(context: Context): RoomSingleton{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    RoomSingleton::class.java,
                    "roomdb")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE as RoomSingleton
        }
    }
}
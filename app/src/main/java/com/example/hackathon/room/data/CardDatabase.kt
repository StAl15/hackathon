package com.example.hackathon.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hackathon.room.models.CardModel


@Database(entities = [CardModel::class], version = 1, exportSchema = false)
abstract class CardDatabase : RoomDatabase() {

    abstract fun CardDao(): CardDao

    companion object {
        @Volatile
        private var INSTANCE: CardDatabase? = null

        fun getDatabase(context: Context): CardDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDatabase::class.java,
                    "Card_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
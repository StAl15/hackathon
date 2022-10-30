package com.example.hackathon.room.models

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey


//entity - сущность, класс модели
@Entity(tableName = "card_table")
data class CardModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val card_name: String,
    val card_image: ByteArray,
    val description: String
)
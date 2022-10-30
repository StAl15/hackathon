package com.example.hackathon.room.models

import android.os.Parcelable
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


//entity - сущность, класс модели
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uid: UUID,
    val name: String,
    val email: String,
    val password: String
)
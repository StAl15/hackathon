package com.example.hackathon.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hackathon.room.models.CardModel
import kotlinx.coroutines.flow.Flow


@Dao
interface CardDao {

    //При появлении одинаковых задач создать задачу
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCard(card: CardModel)

    @Update
    suspend fun updateCard(card: CardModel)

    @Delete
    suspend fun deleteCard(card: CardModel)

    @Query("DELETE FROM card_table")
    suspend fun deleteAllcards()

    @Query("SELECT * FROM card_table ORDER BY card_name DESC")
    fun readAllData(): LiveData<List<CardModel>>

    @Query("SELECT * FROM card_table WHERE id LIKE :searchQuery OR card_name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<CardModel>>
}
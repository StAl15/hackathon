package com.example.hackathon.room.repository

import androidx.lifecycle.LiveData
import com.example.hackathon.room.data.CardDao
import com.example.hackathon.room.models.CardModel
import kotlinx.coroutines.flow.Flow

class CardRepository(private val CardDao: CardDao) {

    val readAllData: LiveData<List<CardModel>> = CardDao.readAllData()

    suspend fun addCard(Card: CardModel){
        CardDao.addCard(Card)
    }

    suspend fun updateCard(Card: CardModel){
        CardDao.updateCard(Card)
    }

    suspend fun deleteCard(Card: CardModel){
        CardDao.deleteCard(Card)
    }

    suspend fun deleteAllCards(){
        CardDao.deleteAllcards()
    }

    fun searchDatabase(searchQuery: String): Flow<List<CardModel>> {
        return CardDao.searchDatabase(searchQuery)
    }

}
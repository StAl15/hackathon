package com.example.hackathon.room.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hackathon.room.data.CardDatabase
import com.example.hackathon.room.models.CardModel
import com.example.hackathon.room.repository.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<CardModel>>
    private val repository: CardRepository

    init {
        val cardDao = CardDatabase.getDatabase(
            application
        ).CardDao()
        repository = CardRepository(cardDao)
        readAllData = repository.readAllData
    }

    fun addCard(Card: CardModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCard(Card)
        }
    }

    fun updateCard(Card: CardModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCard(Card)
        }
    }

    fun deleteCard(Card: CardModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCard(Card)
        }
    }

    fun deleteAllCards() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllCards()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<CardModel>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}
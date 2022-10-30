package com.example.hackathon.room.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.hackathon.room.data.UserDatabase
import com.example.hackathon.room.models.User
import com.example.hackathon.room.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(
            application
        ).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(User: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(User)
        }
    }

    fun updateUser(User: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(User)
        }
    }

    fun deleteUser(User: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(User)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<User>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}
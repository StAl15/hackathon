package com.example.hackathon.room.repository

import androidx.lifecycle.LiveData
import com.example.hackathon.room.data.UserDao
import com.example.hackathon.room.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val UserDao: UserDao) {

    val readAllData: LiveData<List<User>> = UserDao.readAllData()

    suspend fun addUser(User: User){
        UserDao.addUser(User)
    }

    suspend fun updateUser(User: User){
        UserDao.updateUser(User)
    }

    suspend fun deleteUser(User: User){
        UserDao.deleteUser(User)
    }

    suspend fun deleteAllUsers(){
        UserDao.deleteAllusers()
    }

    fun searchDatabase(searchQuery: String): Flow<List<User>> {
        return UserDao.searchDatabase(searchQuery)
    }

}
package com.example.hackathon.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hackathon.room.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    //При появлении одинаковых задач создать задачу
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllusers()

    @Query("SELECT * FROM user_table ORDER BY name DESC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE UID LIKE :searchQuery OR email LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<User>>
}


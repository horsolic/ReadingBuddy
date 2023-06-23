package com.readingbuddy.db

import com.readingbuddy.models.Items
import com.readingbuddy.models.Status

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Items)

    @Query("SELECT * FROM Books")
    fun getAllItems(): Flow<List<Items>>

    @Query("SELECT * FROM Books WHERE status = :status")
    fun getAllItemsByStatus(status: Status): Flow<List<Items>>

    @Query("DELETE FROM Books")
    suspend fun deleteAll()

    @Query("DELETE FROM Books WHERE id = :id")
    suspend fun deleteItemById(id: String)
}
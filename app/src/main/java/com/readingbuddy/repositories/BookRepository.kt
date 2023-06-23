package com.readingbuddy.repositories

import com.readingbuddy.db.BookDatabase
import com.readingbuddy.models.Book
import com.readingbuddy.models.Items
import com.readingbuddy.models.Status
import com.readingbuddy.networking.BookApi

import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookApi: BookApi, private val bookDatabase: BookDatabase) {
    suspend fun getBooksList(query: String, startIndex: Int): ApiResponse<Book?> {
        return bookApi.getBooks(
            query = query,
            startIndex = startIndex,
            maxResults = 20,
            apiKey = "AIzaSyAp4FNqMSThS2FWMpE2fKhJ0tKkTbYpbBc",
        )
    }

    suspend fun insertItem(item: Items) {
        bookDatabase.bookDao().insertItem(item)
    }

    fun getItems(): Flow<List<Items>> = bookDatabase.bookDao().getAllItems()

    suspend fun deleteAll() {
        bookDatabase.bookDao().deleteAll()
    }

    fun getItemsByStatus(status: Status): Flow<List<Items>> {
        return bookDatabase.bookDao().getAllItemsByStatus(status)
    }

    suspend fun deleteItemById(id: String) {
        bookDatabase.bookDao().deleteItemById(id)
    }
}
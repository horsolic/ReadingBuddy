package com.readingbuddy.ui.components.main

import android.app.Application
import androidx.compose.runtime.mutableStateOf

import com.readingbuddy.db.BookDatabase
import com.readingbuddy.networking.BookApi
import com.readingbuddy.networking.RetrofitClient
import com.readingbuddy.repositories.BookRepository
import com.readingbuddy.utils.PrefsHelper

enum class ThemeMode {
    LIGHT, DARK, AUTO
}

class ReadingBuddyApplication : Application() {
    lateinit var bookRepository: BookRepository

    val themeMode = mutableStateOf(ThemeMode.AUTO)

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val bookApi = RetrofitClient.getInstance().create(BookApi::class.java)
        val database = BookDatabase.getDatabase(applicationContext)
        bookRepository = BookRepository(bookApi, database)

        PrefsHelper.initPrefs(applicationContext)
    }
}
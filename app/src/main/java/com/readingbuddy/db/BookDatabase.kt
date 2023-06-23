package com.readingbuddy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.readingbuddy.models.Items
import com.readingbuddy.utils.Constants

@Database(entities = [Items::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        BookDatabase::class.java,
                        Constants.DB_NAME)
                        .setJournalMode(JournalMode.TRUNCATE)
                        .fallbackToDestructiveMigration()
                    .build()
                }
            }

            return INSTANCE!!
        }
    }
}
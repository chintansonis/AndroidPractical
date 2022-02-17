package com.example.chintanandroidpractical.di

import android.content.Context
import androidx.room.Room
import com.example.chintanandroidpractical.database.AppDatabase
import com.example.chintanandroidpractical.database.SummaryDao
import com.example.chintanandroidpractical.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DATABASE_NAME)
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideSummaryDao(appDatabase: AppDatabase): SummaryDao {
        return appDatabase.summaryDao()
    }
}

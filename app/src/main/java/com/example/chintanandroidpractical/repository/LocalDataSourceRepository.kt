package com.example.chintanandroidpractical.repository

import com.example.chintanandroidpractical.database.SummaryDao
import com.example.chintanandroidpractical.models.entities.FavouriteSummary


class LocalDataSourceRepository constructor(
    private val summaryDao: SummaryDao
) : Repository  {
    fun getFavoriteSummary(): List<FavouriteSummary> {
        return summaryDao.getFavouriteList()
    }

    suspend fun insertFavoriteSummary(favouriteSummary: FavouriteSummary) {
        summaryDao.insertFavouriteSummary(favouriteSummary)
    }

}
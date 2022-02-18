package com.example.chintanandroidpractical.repository
import com.example.chintanandroidpractical.database.SummaryDao
import com.example.chintanandroidpractical.models.entities.FavouriteSummary
import com.example.chintanandroidpractical.models.entities.Summary


// local repository for DB operations
class LocalDataSourceRepository constructor(
    private val summaryDao: SummaryDao
) : Repository  {

    suspend fun getFavoriteSummary(): List<FavouriteSummary> {
        return summaryDao.getFavouriteList()
    }

    suspend fun getSummaryObject(id: Int): Summary {
        return summaryDao.getSummary(id)
    }

    suspend fun updateSummaryObject(summary: Summary) {
        return summaryDao.updateSummary(summary)
    }

    suspend fun insertFavoriteSummary(favouriteSummary: FavouriteSummary) {
        summaryDao.insertFavouriteSummary(favouriteSummary)
    }
     fun deleteFavoriteSummary(favouriteSummary: FavouriteSummary) {
        summaryDao.deleteFavoriteSummary(favouriteSummary)
    }



}
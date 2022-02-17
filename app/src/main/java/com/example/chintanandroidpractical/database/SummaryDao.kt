package com.example.chintanandroidpractical.database

import androidx.room.*
import com.example.chintanandroidpractical.models.entities.FavouriteSummary
import com.example.chintanandroidpractical.models.entities.Summary

@Dao
interface SummaryDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSummaryList(movies: List<Summary>)

  @Query("SELECT * FROM Summary")
  suspend fun getSummaryList(): List<Summary>


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFavouriteSummary(favouriteSummary: FavouriteSummary)

  @Delete
  suspend fun deleteFavoriteSummary(favouriteSummary: FavouriteSummary)

  @Query("SELECT * FROM favorite_table")
   fun getFavouriteList(): List<FavouriteSummary>


}

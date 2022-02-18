package com.example.chintanandroidpractical.database

import androidx.room.*
import com.example.chintanandroidpractical.models.entities.FavouriteSummary
import com.example.chintanandroidpractical.models.entities.Summary

@Dao
interface SummaryDao {
    // insert fresh products into Summary DBtable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSummaryList(movies: List<Summary>)

    // fetch fresh products from Summary DBTable
    @Query("SELECT * FROM Summary")
    suspend fun getSummaryList(): List<Summary>

    // fetch fresh product from Summary DBTable
    @Query("SELECT * FROM Summary WHERE id = :id_")
    suspend fun getSummary(id_: Int): Summary

    // update object in summary table to notify UI that it has selected as favourite
    @Update
    suspend fun updateSummary(summary: Summary)

    // insert favourite item in FavouriteSummary dbtable
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteSummary(favouriteSummary: FavouriteSummary)

    // delete favourite item from FavouriteSummary dbtable on user click from favourite or main product list
    @Delete
    fun deleteFavoriteSummary(summary: FavouriteSummary)

    // fetch all fav list to dispay at UI
    @Query("SELECT * FROM FavouriteSummary")
    suspend  fun getFavouriteList(): List<FavouriteSummary>


}

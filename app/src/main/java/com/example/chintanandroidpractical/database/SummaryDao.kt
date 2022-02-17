package com.example.chintanandroidpractical.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.chintanandroidpractical.models.entities.Summary

@Dao
interface SummaryDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSummaryList(movies: List<Summary>)

  @Query("SELECT * FROM Summary")
  //@Query("SELECT * FROM Summary WHERE `limit` = :limit_")
  suspend fun getSummaryList(): List<Summary>
  //suspend fun getSummaryList(limit_: Int): List<Summary>
}

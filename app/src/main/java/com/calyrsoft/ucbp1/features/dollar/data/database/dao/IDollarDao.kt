// features/dollar/data/database/dao/IDollarDao.kt
package com.calyrsoft.ucbp1.features.dollar.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity

@Dao
interface IDollarDao {
    @Query("SELECT * FROM dollars ORDER BY timestamp DESC")
    suspend fun getList(): List<DollarEntity>

    @Query("SELECT * FROM dollars ORDER BY timestamp DESC LIMIT :limit")
    suspend fun getHistory(limit: Int = 50): List<DollarEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dollar: DollarEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDollars(lists: List<DollarEntity>)

    @Query("DELETE FROM dollars")
    suspend fun deleteAll()
}

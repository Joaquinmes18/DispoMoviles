// features/dollar/data/database/AppRoomDatabase.kt
package com.calyrsoft.ucbp1.features.dollar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.calyrsoft.ucbp1.features.dollar.data.database.dao.IDollarDao
import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity

@Database(entities = [DollarEntity::class], version = 2, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun dollarDao(): IDollarDao

    companion object {
        @Volatile private var Instance: AppRoomDatabase? = null

        // MIGRACIÓN 1→2: agregamos columnas usdt y usdc (REAL)
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE dollars ADD COLUMN usdt REAL")
                db.execSQL("ALTER TABLE dollars ADD COLUMN usdc REAL")
            }
        }

        fun getDatabase(context: Context): AppRoomDatabase =
            Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppRoomDatabase::class.java, "dollar_db")
                    .addMigrations(MIGRATION_1_2)
                    // .fallbackToDestructiveMigration() // solo si aceptas borrar datos
                    .build()
                    .also { Instance = it }
            }
    }
}

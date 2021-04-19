package com.tahmeel.task.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tahmeel.task.model.Order

/**
 * Created by @mohamedebrahim96 on 16,April,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */

@Database(entities = [Order::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tahmeelDao(): TahmeelDao
}

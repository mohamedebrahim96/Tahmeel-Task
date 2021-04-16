package com.tahmeel.task.persistence


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tahmeel.task.model.Order

@Dao
interface TahmeelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<Order>)

//    @Query("SELECT * FROM Order WHERE page = :page_")
//    suspend fun getOrdersList(page_: Int): List<Order>

    @Query("SELECT * FROM `Order`")
    suspend fun getAllOrdersList(): List<Order>
}
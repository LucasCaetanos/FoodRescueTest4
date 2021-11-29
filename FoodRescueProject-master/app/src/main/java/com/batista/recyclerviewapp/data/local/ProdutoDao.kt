package com.batista.recyclerviewapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProdutoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProduct(produtoEntity: ProdutoEntity)

    @Query("SELECT * FROM produto ORDER BY id ASC")
    fun getAllProducts(): List<ProdutoEntity>

}
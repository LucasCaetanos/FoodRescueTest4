package com.batista.recyclerviewapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produto")
data class ProdutoEntity (

    val nomeProduto: String,
    val data: String,
    val quantidade: String,
    val status: String,

) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
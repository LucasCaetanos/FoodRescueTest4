package com.batista.recyclerviewapp.data.local

import com.batista.recyclerviewapp.model.Produto

class ProductLocalSource (private val produtoDao: ProdutoDao) {

    fun getAllProducts(): List<Produto> {
        return produtoDao.getAllProducts().map { produtoEntity -> Produto(
            produtoEntity.nomeProduto,
            produtoEntity.data,
            produtoEntity.quantidade,
            produtoEntity.status) }
    }

    fun addProduct(produto: Produto) {
        produtoDao.addProduct(
            ProdutoEntity(
                produto.nomeProduto,
                produto.data,
                produto.quantidade,
                produto.status
        ))
    }
}
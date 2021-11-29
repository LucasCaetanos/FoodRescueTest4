package com.batista.recyclerviewapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.batista.recyclerviewapp.R
import com.batista.recyclerviewapp.model.Produto

class AdapterProduto(private val context: Context, private val produtos: MutableList<Produto>): RecyclerView.Adapter<AdapterProduto.ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.fragment_item_list, parent, false)
        val holder = ProdutoViewHolder(itemLista)
        return holder
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.nomeProduto.text = produtos[position].nomeProduto
        holder.data.text = produtos[position].data
        holder.quantidade.text = produtos[position].quantidade
        holder.status.text = produtos[position].status


    }

    override fun getItemCount(): Int = produtos.size

    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeProduto = itemView.findViewById<TextView>(R.id.textNomeProduto)
        val data = itemView.findViewById<TextView>(R.id.dataText)
        val quantidade = itemView.findViewById<TextView>(R.id.textQuantidade)
        val status = itemView.findViewById<TextView>(R.id.textStatus)
    }
}
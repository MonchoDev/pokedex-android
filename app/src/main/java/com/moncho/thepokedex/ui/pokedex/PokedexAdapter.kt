package com.moncho.thepokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moncho.thepokedex.R
import com.moncho.thepokedex.databinding.CardPokedexBinding
import com.moncho.thepokedex.service.Results

class PokedexAdapter(): RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>() {

    var pokedexList = emptyList<Results>()

    fun setData(list: List<Results>){
        pokedexList = list

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexAdapter.PokedexViewHolder {
        return PokedexViewHolder(CardPokedexBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PokedexAdapter.PokedexViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.pokedexTextView).text = pokedexList[position].name
    }

    override fun getItemCount(): Int {
        return pokedexList.count()
    }

    class PokedexViewHolder(itemBinding: CardPokedexBinding): RecyclerView.ViewHolder(itemBinding.root)
}
package com.moncho.thepokedex.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moncho.thepokedex.R
import com.moncho.thepokedex.databinding.CardFavBinding
import com.moncho.thepokedex.databinding.CardPokedexBinding
import com.moncho.thepokedex.service.PokemonResult
import com.moncho.thepokedex.service.PokemonResults


class FavsAdapter(val context: Context): RecyclerView.Adapter<FavsAdapter.FavsViewHolder>() {

    var list = ArrayList<PokemonResult>()

    fun setData(list: ArrayList<PokemonResult>){
        this.list = list

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavsAdapter.FavsViewHolder {
        return FavsViewHolder(CardFavBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavsAdapter.FavsViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.nameTextView).text = list[position].name
        val imageView = holder.itemView.findViewById<ImageView>(R.id.pokemonImageView)
        Glide
            .with(context)
            .load(list[position].sprites!!.frontDefault)
            .into(imageView)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    class FavsViewHolder(itemBinding: CardFavBinding): RecyclerView.ViewHolder(itemBinding.root)
}
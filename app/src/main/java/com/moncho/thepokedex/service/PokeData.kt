package com.moncho.thepokedex.service

import com.google.gson.annotations.SerializedName

data class AllPokemonResult(
    @SerializedName("count") var count : Int? = null,
    @SerializedName("next") var next : String? = null,
    @SerializedName("previous") var previous : String? = null,
    @SerializedName("results") var results  : ArrayList<PokemonResults> = arrayListOf()
)

data class PokemonResults(
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("url"  ) var url  : String? = null
)

data class PokemonResult(
    @SerializedName("name") var name: String? = null,
    @SerializedName("sprites") var sprites: Sprites? = null
)

data class Sprites(
    @SerializedName("front_default") var frontDefault: String? = null
)
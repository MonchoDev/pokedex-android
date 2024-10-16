package com.moncho.thepokedex.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon?limit=100000&offset=0")
    fun listAllPokemon(): Call<AllPokemonResult>

    @GET("pokemon/{name}")
    fun searchPokemon(@Path("name") name: String) : Call<PokemonResult>
}
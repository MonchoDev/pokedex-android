package com.moncho.thepokedex.service

import com.moncho.thepokedex.service.ApiResult
import retrofit2.Call
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon?limit=100000&offset=0")
    fun listAllPokemon(): Call<ApiResult>
}
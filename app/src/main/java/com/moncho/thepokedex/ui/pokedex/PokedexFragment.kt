package com.moncho.thepokedex.ui.pokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.moncho.thepokedex.databinding.FragmentPokedexBinding
import com.moncho.thepokedex.service.ApiResult
import com.moncho.thepokedex.service.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokedexFragment : Fragment() {

    private var _binding: FragmentPokedexBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPokedexBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pokedexRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.pokedexRecyclerView.adapter = PokedexAdapter()

        //Retrofit:

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: PokeApiService = retrofit.create(PokeApiService::class.java)

        val call = service.listAllPokemon()

        call.enqueue(object : Callback<ApiResult> {
            override fun onResponse(call: Call<ApiResult>, response: Response<ApiResult>) {
                Log.d("Pokemon", response.body().toString())

                (binding.pokedexRecyclerView.adapter as PokedexAdapter).setData(response.body()!!.results)
            }

            override fun onFailure(call: Call<ApiResult>, t: Throwable) {
                call.cancel()
            }
        })


    }
}
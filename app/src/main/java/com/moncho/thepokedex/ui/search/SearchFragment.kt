package com.moncho.thepokedex.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.moncho.thepokedex.databinding.FragmentSearchBinding
import com.moncho.thepokedex.service.PokeApiService
import com.moncho.thepokedex.service.PokemonResult
import com.moncho.thepokedex.ui.pokedex.PokedexAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: PokeApiService = retrofit.create(PokeApiService::class.java)

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val pokemonName = binding.pokemonText.text.toString()
            searchPokemon(pokemonName)
        }

    }

    private fun searchPokemon(name: String){

        val call = service.searchPokemon(name)

        call.enqueue(object : Callback<PokemonResult> {
            override fun onResponse(call: Call<PokemonResult>, response: Response<PokemonResult>) {
                Log.d("Pokemon", response.body().toString())

                Glide
                    .with(requireParentFragment())
                    .load(response.body()!!.sprites!!.frontDefault)
                    .into(binding.pokemonImageView)
            }

            override fun onFailure(call: Call<PokemonResult>, t: Throwable) {
                call.cancel()
            }
        })
    }
}
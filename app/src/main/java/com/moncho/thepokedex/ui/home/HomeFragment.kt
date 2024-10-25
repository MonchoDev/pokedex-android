package com.moncho.thepokedex.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.moncho.thepokedex.databinding.FragmentHomeBinding
import com.moncho.thepokedex.service.PokemonResult
import com.moncho.thepokedex.service.Sprites
import com.moncho.thepokedex.ui.pokedex.PokedexAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    var favs = ArrayList<PokemonResult>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = com.google.firebase.Firebase.auth

        val db = Firebase.firestore

        binding.favsRV.layoutManager = LinearLayoutManager(activity)
        binding.favsRV.adapter = FavsAdapter(requireContext())

        db.collection("usuarios")
            .document(auth.currentUser!!.uid)
            .collection("favs").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Favs", "${document.id} => ${document.data}")
                    val fav = document.toObject<PokemonResult>()
                    favs.add(fav)
                }

                (binding.favsRV.adapter as FavsAdapter).setData(favs)
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.hackathon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.adapters.CollectionAdapter
import com.example.hackathon.room.viewmodel.CardViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


class Collection : Fragment() {

    private lateinit var mCardViewModel: CardViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_collection, container, false)

        val adapter = CollectionAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.list_cards)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mCardViewModel = ViewModelProvider(this).get(CardViewModel::class.java)

        try {
            mCardViewModel.readAllData.observe(
                viewLifecycleOwner,
                Observer { collection -> adapter.setData(collection) }
            )
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()

        }


        val btnAddCard: ExtendedFloatingActionButton = view.findViewById(R.id.addCardbtn)
        btnAddCard.setOnClickListener {
            findNavController().navigate(R.id.action_collection_to_addCard2)
        }







        return view
    }

}
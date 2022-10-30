package com.example.hackathon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


class Collection : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_collection, container, false)

        val btnAddCard: ExtendedFloatingActionButton = view.findViewById(R.id.addCardbtn)
        btnAddCard.setOnClickListener {
            findNavController().navigate(R.id.action_collection_to_addCard2)
        }





        return view
    }

}
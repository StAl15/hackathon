package com.example.hackathon.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.room.models.CardModel
import com.example.hackathon.room.models.User
import com.google.android.material.button.MaterialButton

class CollectionAdapter(
) : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    private var collection = emptyList<CardModel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
//            Glide.with(this).load(collection[position].card_image).into(findViewById(R.id.imgcard1))
            var bitmap1: Bitmap = BitmapFactory.decodeByteArray(
                collection[position].card_image,
                0,
                collection[position].card_image.size
            )
            findViewById<ImageView>(R.id.imgcard1).setImageBitmap(bitmap1)


            findViewById<MaterialButton>(R.id.btn1).text = collection[position].card_name

//            Glide.with(this).load(collection[position + 1].card_image)
//                .into(findViewById(R.id.imgcard2))
            var bitmap2: Bitmap = BitmapFactory.decodeByteArray(
                collection[position+1].card_image,
                0,
                collection[position+1].card_image.size
            )
            findViewById<ImageView>(R.id.imgcard2).setImageBitmap(bitmap2)
            findViewById<MaterialButton>(R.id.btn2).text = collection[position + 1].card_name
        }
    }

    override fun getItemCount(): Int {
        return collection.size
    }

    fun setData(new_collection: List<CardModel>) {
        this.collection = new_collection
        notifyDataSetChanged()
    }
}
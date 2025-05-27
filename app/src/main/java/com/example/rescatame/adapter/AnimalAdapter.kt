package com.example.rescatame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rescatame.R
import com.example.rescatame.model.Animal

class AnimalAdapter(
    private val animales: List<Animal>,
    private val onItemClick: (Animal) -> Unit
) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.nombreAnimal)
        val imagenImageView: ImageView = view.findViewById(R.id.imagenAnimal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animales[position]
        val context = holder.itemView.context

        holder.nombreTextView.text = animal.nombre

        val resId = context.resources.getIdentifier(animal.imagenUrl, "drawable", context.packageName)
        if (resId != 0) {
            Glide.with(context)
                .load(resId)
                .placeholder(R.drawable.animal_image)
                .into(holder.imagenImageView)
        } else {
            Glide.with(context)
                .load(R.drawable.animal_image)
                .into(holder.imagenImageView)
        }

        holder.itemView.setOnClickListener { onItemClick(animal) }
    }

    override fun getItemCount(): Int = animales.size
}

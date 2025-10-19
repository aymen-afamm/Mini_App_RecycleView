package com.example.tp_05

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(
    private val animaux: MutableList<Animal>,
    private val onDetailsClick: (Animal) -> Unit,
    private val onSupprimerClick: (Int) -> Unit
) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewAnimal)
        val textViewNom: TextView = itemView.findViewById(R.id.textViewNom)
        val textViewEspece: TextView = itemView.findViewById(R.id.textViewEspece)
        val buttonDetails: Button = itemView.findViewById(R.id.buttonDetails)
        val buttonSupprimer: Button = itemView.findViewById(R.id.buttonSupprimer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.animal_item, parent, false)
        return AnimalViewHolder(view)
    }
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animaux[position]

        holder.imageView.setImageResource(animal.imageResId)
        holder.textViewNom.text = animal.nom
        holder.textViewEspece.text = animal.espece
        holder.buttonDetails.setOnClickListener {
            onDetailsClick(animal)
        }

        holder.buttonSupprimer.setOnClickListener {
            onSupprimerClick(position)
        }
    }
    override fun getItemCount(): Int = animaux.size
    fun supprimerAnimal(position: Int) {
        animaux.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, animaux.size)
    }
}
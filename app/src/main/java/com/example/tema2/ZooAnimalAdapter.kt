package com.example.tema2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ZooAnimalAdapter(val animalsList: ArrayList<ZooAnimalModel>): RecyclerView.Adapter<ZooAnimalAdapter.ZooAnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZooAnimalViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.animals, parent, false)
        val zooAnimalViewHolder = ZooAnimalViewHolder(view)

        Log.e("ZooAnimalAdapter", "onCreateViewHolder")

        return zooAnimalViewHolder
    }

    override fun onBindViewHolder(holder: ZooAnimalViewHolder, position: Int) {
        val zooAnimalModel: ZooAnimalModel = animalsList[position]
        holder.bind(zooAnimalModel)
        Log.e("ZooAnimalAdapter", "onBindViewHolder: $position")
    }

    override fun getItemCount(): Int {
        return this.animalsList.size
    }

    private val zooAnimalRepository = ZooAnimalRepository()

    fun deleteZooAnimal(zooAnimal: ZooAnimal,position: Int) {
        val onSuccessListener = object : ZooAnimalRepository.OnSuccessListener {
            override fun onSuccess() {
                animalsList.remove(zooAnimal.convert())
                notifyItemRemoved(position)
            }
        }
        zooAnimalRepository.deleteZooAnimal(zooAnimal,onSuccessListener)
    }

    inner class ZooAnimalViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        private var deleteButton: ImageButton? = view.findViewById(R.id.deleteButton)

        private val animal:TextView
        private val continent:TextView
        init{
            animal=view.findViewById<TextView>(R.id.animal)
            continent=view.findViewById<TextView>(R.id.continent)
        }
        fun bind(zooAnimalModel: ZooAnimalModel)
        {
            animal.text = zooAnimalModel.animal
            continent.text = zooAnimalModel.continent
            deleteButton?.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val animal = animalsList[position]
                    deleteZooAnimal(animal.convert(),position)
                    Toast.makeText(itemView.context, "Zoo animal deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
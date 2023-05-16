package com.example.tema2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var addButton: Button? = null
    private var editTextAnimal: EditText? = null
    private var editTextContinent: EditText? = null
    private val zooAnimalRepository = ZooAnimalRepository()
    private val continentsList = listOf(
        "Africa",
        "africa",
        "Antarctica",
        "antarctica",
        "Asia",
        "asia",
        "Australia",
        "australia",
        "Europe",
        "europe",
        "North America",
        "north america",
        "South America",
        "south america"
    )
    private val animalsList by lazy {
        ArrayList<ZooAnimalModel>()
    }

    private var zooAnimalAdapter: ZooAnimalAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        setupRecyclerView()

        addButton = findViewById(R.id.addButton)
        editTextAnimal = findViewById(R.id.enterAnimal)
        editTextContinent = findViewById(R.id.enterDescription)
        addButton?.setOnClickListener {
            insertZooAnimal()
        }
        getZooAnimals()
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        zooAnimalAdapter = ZooAnimalAdapter(animalsList)
        recyclerView.adapter = zooAnimalAdapter
    }

    private fun getZooAnimals() {
        val onGetListener = object : ZooAnimalRepository.OnGetListener {
            override fun onSuccess(items: List<ZooAnimal>) {
                animalsList.clear()
                items.forEach{zooAnimal->
                    animalsList.add(zooAnimal.convert())
                }
                zooAnimalAdapter?.notifyItemRangeChanged(0, animalsList.size)
                Toast.makeText(
                    this@MainActivity, "Success.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        zooAnimalRepository.getAllAnimals(onGetListener)
    }
    private fun insertZooAnimal() {
        val animal = editTextAnimal?.text?.toString() ?: return
        when (animal.isEmpty()) {
            true -> {
                editTextAnimal?.error = getString(R.string.error_required)
                return
            }
            false -> editTextAnimal?.error = null
        }

        val animalExists = animalsList.find{it.animal.lowercase()==animal.lowercase()}
        when (animalExists!=null)
        {
            true -> {
                editTextAnimal?.error = getString(R.string.error_already_exists)
                return
            }
            false -> editTextAnimal?.error = null
        }

        val continent = editTextContinent?.text?.toString() ?: return
        when (continent.isEmpty()) {
            true -> {
                editTextContinent?.error = getString(R.string.error_required)
                return
            }

            false -> editTextContinent?.error = null
        }
        when(continent  !in continentsList)
        {
            true -> {
                editTextContinent?.error = getString(R.string.error_continent)
                return
            }

            false -> editTextContinent?.error = null
        }

        val zooAnimal = ZooAnimal(
            0,
            animal,
            continent
        )
        val onSuccessListener = object : ZooAnimalRepository.OnSuccessListener {
            override fun onSuccess() {
                animalsList.add(zooAnimal.convert())
                zooAnimalAdapter?.notifyItemChanged(animalsList.size - 1)
                Toast.makeText(
                    this@MainActivity, "Success.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        zooAnimalRepository.insertZooAnimal(zooAnimal,onSuccessListener)
    }

}
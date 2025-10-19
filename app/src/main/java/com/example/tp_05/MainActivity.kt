package com.example.tp_05
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnimalAdapter
    private lateinit var listeAnimaux: MutableList<Animal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listeAnimaux = mutableListOf(
            Animal("Chat", "Mammifère", R.drawable.cat),
            Animal("Chien", "Mammifère", R.drawable.chien),
            Animal("Lion", "Mammifère", R.drawable.lion),
            Animal("Aigle", "Oiseau", R.drawable.aigle),
            Animal("Perroquet", "Oiseau", R.drawable.perroquet),
            Animal("Serpent", "Reptile", R.drawable.serpent),
            Animal("Éléphant", "Mammifère", R.drawable.elephant)
        )
        recyclerView = findViewById(R.id.recyclerViewAnimaux)

        adapter = AnimalAdapter(
            listeAnimaux,
            onDetailsClick = { animal -> afficherDetails(animal) },
            onSupprimerClick = { position -> confirmerSuppression(position) }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupAffichage)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioLineaire -> {
                    recyclerView.layoutManager = LinearLayoutManager(this)
                }
                R.id.radioGrille -> {
                    recyclerView.layoutManager = GridLayoutManager(this, 2)
                }
            }
        }
    }
    private fun afficherDetails(animal: Animal) {
        val message = "Nom: ${animal.nom}\nEspèce: ${animal.espece}"

        AlertDialog.Builder(this)
            .setTitle("Détails de l'animal")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun confirmerSuppression(position: Int) {
        val animal = listeAnimaux[position]

        AlertDialog.Builder(this)
            .setTitle("Supprimer")
            .setMessage("Voulez-vous vraiment supprimer ${animal.nom} ?")
            .setPositiveButton("Oui") { _, _ ->
                adapter.supprimerAnimal(position)
                Toast.makeText(this, "${animal.nom} supprimé", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Non", null)
            .show()
    }
}
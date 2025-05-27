package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rescatame.adapter.AnimalAdapter
import com.example.rescatame.dao.AnimalDAO

class ListaAnimalesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_animales)

        val tipo = intent.getStringExtra("tipo") ?: "Perro"
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerAnimales)
        val backButton = findViewById<ImageView>(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }

        val prefs = getSharedPreferences("rescatame_prefs", MODE_PRIVATE)
        val idAsociacion = prefs.getInt("id_asociacion", 1) // ← ID DE LA ASOCIACIÓN ACTUAL

        val dao = AnimalDAO(this)
        val lista = dao.obtenerPorTipoYAsociacion(tipo, idAsociacion)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AnimalAdapter(lista) { animal ->
            val intent = Intent(this, DetalleAnimalActivity::class.java).apply {
                putExtra("nombre", animal.nombre)
                putExtra("edad", animal.edad)
                putExtra("tamano", animal.tamano)
                putExtra("raza", animal.raza)
                putExtra("sexo", animal.sexo)
                putExtra("tipo", animal.tipo)
                putExtra("descripcion", animal.descripcion)
                putExtra("salud", animal.salud)
                putExtra("historia", animal.historia)
                putExtra("imagen_url", animal.imagenUrl)
            }
            startActivity(intent)
        }
    }
}

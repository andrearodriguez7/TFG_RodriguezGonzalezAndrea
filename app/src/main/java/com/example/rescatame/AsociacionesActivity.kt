package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class AsociacionesActivity : AppCompatActivity() {

    private val asociaciones = listOf(
        "Arca de Noé Sevilla",
        "Arca Sevilla",
        "Refugio Escuela Marlene",
        "Asociación La Sonrisa Animal",
        "Unión Protectora de Animales",
        "Asociación Gatas Salvajes",
        "Asociación El Albergue"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asociaciones_activity) // Asegúrate que se llama así tu XML

        // Flecha para volver
        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, PantallaUsuario::class.java)
            startActivity(intent)
            finish()
        }

        // Lista de asociaciones
        val listView = findViewById<ListView>(R.id.listaAsociaciones)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, asociaciones)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, _, _ ->
            // Siempre abre la misma protectora, independientemente del nombre pulsado
            val intent = Intent(this, InicioProtectora::class.java)
            startActivity(intent)
        }
    }
}

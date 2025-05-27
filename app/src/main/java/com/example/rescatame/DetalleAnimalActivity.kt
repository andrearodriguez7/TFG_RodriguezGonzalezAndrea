package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DetalleAnimalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_animal)

        val nombre = intent.getStringExtra("nombre")
        val edad = intent.getStringExtra("edad")
        val tamano = intent.getStringExtra("tamano")
        val raza = intent.getStringExtra("raza")
        val sexo = intent.getStringExtra("sexo")
        val descripcion = intent.getStringExtra("descripcion")
        val salud = intent.getStringExtra("salud")
        val historia = intent.getStringExtra("historia")
        val imagenUrl = intent.getStringExtra("imagen_url") ?: ""

        findViewById<TextView>(R.id.animalName).text = nombre
        findViewById<TextView>(R.id.animalAge).text = "Edad: $edad"
        findViewById<TextView>(R.id.animalSize).text = "Tamaño: $tamano"
        findViewById<TextView>(R.id.animalBreed).text = "Raza: $raza"
        findViewById<TextView>(R.id.animalGender).text = "Sexo: $sexo"
        findViewById<TextView>(R.id.animalDescription).text = descripcion
        findViewById<TextView>(R.id.animalHealth).text = salud
        findViewById<TextView>(R.id.animalHistory).text = historia

        val imageView = findViewById<ImageView>(R.id.imageAnimal)
        val resId = resources.getIdentifier(imagenUrl, "drawable", packageName)
        if (resId != 0) {
            imageView.setImageResource(resId)
        } else {
            imageView.setImageResource(R.drawable.animal_image)
        }

        findViewById<ImageButton>(R.id.backButtonHistorial).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.contactButton).setOnClickListener {
            Toast.makeText(this, "Funcionalidad de contacto aún no disponible", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageButton>(R.id.btnHome).setOnClickListener {
            startActivity(Intent(this, PantallaUsuario::class.java))
            finish()
        }

        findViewById<ImageButton>(R.id.btnPerfil).setOnClickListener {
            startActivity(Intent(this, PerfilUsuario::class.java))
            finish()
        }
    }
}

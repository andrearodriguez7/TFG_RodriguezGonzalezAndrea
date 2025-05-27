package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class InicioAsociacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio_asociacion_layout)

        val btnPerfil = findViewById<Button>(R.id.btnPerfil)
        val btnAnimales = findViewById<Button>(R.id.btnAsociaciones)
        val backButton = findViewById<ImageView>(R.id.backButton)

        btnPerfil.setOnClickListener {
            startActivity(Intent(this, AjustesPerfilAsociacion::class.java))
        }

        btnAnimales.setOnClickListener {
            startActivity(Intent(this, GestionAnimalesActivity::class.java))
        }

        // Al pulsar la flecha, se cierra sesión
        backButton.setOnClickListener {
            startActivity(Intent(this, IniciarSesion::class.java))
            finish()
        }
    }
}

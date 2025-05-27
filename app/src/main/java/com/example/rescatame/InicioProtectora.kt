package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class InicioProtectora : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicioprotectora_layout)

        // Botones de elección de animal
        val btnPerros = findViewById<Button>(R.id.btnDogs)
        val btnGatos = findViewById<Button>(R.id.btnCats)

        // Botones de navegación inferior
        val btnHome = findViewById<ImageButton>(R.id.btnHome)
        val btnPerfil = findViewById<ImageButton>(R.id.btnPerfil)

        // Ir a Perros
        btnPerros.setOnClickListener {
            val intent = Intent(this, PerrosActivity::class.java)
            startActivity(intent)
        }

        // Ir a Gatos
        btnGatos.setOnClickListener {
            val intent = Intent(this, GatosActivity::class.java)
            startActivity(intent)
        }

        // Ir a PantallaInicio (inicio general)
        btnHome.setOnClickListener {
            val intent = Intent(this, PantallaUsuario::class.java)
            startActivity(intent)
            finish()
        }

        // Ir al perfil de la asociación
        btnPerfil.setOnClickListener {
            val intent = Intent(this, PerfilUsuario::class.java)
            startActivity(intent)
        }
    }
}

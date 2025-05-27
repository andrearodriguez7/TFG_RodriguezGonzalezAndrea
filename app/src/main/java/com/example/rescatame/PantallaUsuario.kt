package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PantallaUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.inicio_usuario_layout)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botones
        val btnPerfil = findViewById<Button>(R.id.btnPerfil)
        val btnAsociaciones = findViewById<Button>(R.id.btnAsociaciones)
        val backButton = findViewById<ImageView>(R.id.backButton)

        btnPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilUsuario::class.java))
        }

        btnAsociaciones.setOnClickListener {
            startActivity(Intent(this, AsociacionesActivity::class.java))
        }

        backButton.setOnClickListener {
            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
            finish()
        }
    }
}

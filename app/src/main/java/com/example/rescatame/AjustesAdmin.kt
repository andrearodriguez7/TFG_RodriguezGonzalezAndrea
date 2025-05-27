package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AjustesAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes_admin)

        val btnGestionarUsuarios = findViewById<Button>(R.id.btnGestionarUsuarios)
        val btnGestionarAsociaciones = findViewById<Button>(R.id.btnGestionarAsociaciones)
        val backButton = findViewById<ImageView>(R.id.backButton)

        btnGestionarUsuarios.setOnClickListener {
            startActivity(Intent(this, UsuariosAdminActivity::class.java))
        }

        btnGestionarAsociaciones.setOnClickListener {
            startActivity(Intent(this, AsociacionesAdminActivity::class.java))
        }

        // Flecha para cerrar sesión
        backButton.setOnClickListener {
            startActivity(Intent(this, IniciarSesion::class.java))
            finish()
        }
    }
}

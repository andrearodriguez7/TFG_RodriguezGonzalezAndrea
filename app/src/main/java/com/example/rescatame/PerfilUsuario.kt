package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PerfilUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes_perfil)

        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvCorreo = findViewById<TextView>(R.id.tvCorreo)
        val tvTelefono = findViewById<TextView>(R.id.tvTelefono)
        val tvDireccion = findViewById<TextView>(R.id.tvDireccion)
        val ivPerfil = findViewById<ImageView>(R.id.ivPerfil)
        val btnCambiarContrasena = findViewById<Button>(R.id.btnCambiarContrasena)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        val backButton = findViewById<ImageView>(R.id.backButton)

        val prefs = getSharedPreferences("rescatame_prefs", MODE_PRIVATE)
        val nombre = prefs.getString("usuario_nombre", "Nombre no disponible")
        val correo = prefs.getString("usuario_correo", "Correo no disponible")
        val telefono = prefs.getString("usuario_telefono", "Teléfono no disponible")

        tvNombre.text = "Nombre: $nombre"
        tvCorreo.text = "Correo: $correo"
        tvTelefono.text = "Teléfono: $telefono"
        tvDireccion.text = "Dirección: No disponible"
        ivPerfil.setImageResource(R.drawable.usuario)

        btnCambiarContrasena.setOnClickListener {
            Toast.makeText(this, "Funcionalidad aún no implementada", Toast.LENGTH_SHORT).show()
        }

        btnCerrarSesion.setOnClickListener {
            prefs.edit().clear().apply()
            val intent = Intent(this, IniciarSesion::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        backButton.setOnClickListener {
            startActivity(Intent(this, PantallaUsuario::class.java))
            finish()
        }
    }
}

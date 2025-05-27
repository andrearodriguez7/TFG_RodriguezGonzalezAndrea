package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AjustesPerfilAsociacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes_perfil_asociacion)

        val tvNombre = findViewById<TextView>(R.id.tvNombreAsociacion)
        val tvCorreo = findViewById<TextView>(R.id.tvCorreoAsociacion)
        val tvTelefono = findViewById<TextView>(R.id.tvTelefonoAsociacion)
        val tvDireccion = findViewById<TextView>(R.id.tvDireccionAsociacion)
        val ivFoto = findViewById<ImageView>(R.id.ivAsociacion)
        val btnCambiarContrasena = findViewById<Button>(R.id.btnCambiarContrasenaAsociacion)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesionAsociacion)
        val backButton = findViewById<ImageView>(R.id.backButton)

        val prefs = getSharedPreferences("rescatame_prefs", MODE_PRIVATE)
        val nombre = prefs.getString("asociacion_nombre", "Nombre no disponible")
        val correo = prefs.getString("asociacion_correo", "Correo no disponible")
        val telefono = prefs.getString("asociacion_telefono", "Teléfono no disponible")
        val direccion = prefs.getString("asociacion_direccion", "Dirección no disponible")

        tvNombre.text = "Asociación: $nombre"
        tvCorreo.text = "Correo: $correo"
        tvTelefono.text = "Teléfono: $telefono"
        tvDireccion.text = "Dirección: $direccion"
        ivFoto.setImageResource(R.drawable.logo)

        btnCambiarContrasena.setOnClickListener {
            Toast.makeText(this, "Funcionalidad no implementada todavía", Toast.LENGTH_SHORT).show()
        }

        btnCerrarSesion.setOnClickListener {
            prefs.edit().clear().apply()
            startActivity(Intent(this, IniciarSesion::class.java))
            finish()
        }

        backButton.setOnClickListener {
            startActivity(Intent(this, InicioAsociacionActivity::class.java))
            finish()
        }
    }
}

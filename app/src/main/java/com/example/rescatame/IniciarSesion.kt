package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rescatame.dao.AdministradorDAO
import com.example.rescatame.dao.AsociacionDAO
import com.example.rescatame.dao.UsuarioDAO

class IniciarSesion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.iniciarsesion_layout)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvCrearCuenta = findViewById<TextView>(R.id.tvCrearCuenta)
        val btnEntrar = findViewById<Button>(R.id.btnIniciarSesion)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)

        val usuarioDAO = UsuarioDAO(this)
        val asociacionDAO = AsociacionDAO(this)
        val administradorDAO = AdministradorDAO(this)

        tvCrearCuenta.setOnClickListener {
            startActivity(Intent(this, Registro::class.java))
        }

        btnEntrar.setOnClickListener {
            val correo = etCorreo.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("rescatame_prefs", MODE_PRIVATE)

            when {
                administradorDAO.validarAdministrador(correo, contrasena)?.let { admin ->
                    prefs.edit().apply {
                        putString("tipo", "admin")
                        putString("admin_nombre", admin.nombre)
                        putString("admin_correo", admin.correo)
                        apply()
                    }
                    startActivity(Intent(this, AjustesAdmin::class.java))
                    finish()
                } != null -> {}

                asociacionDAO.validarAsociacion(correo, contrasena)?.let { asociacion ->
                    prefs.edit().apply {
                        putString("tipo", "asociacion")
                        putString("asociacion_nombre", asociacion.nombre)
                        putString("asociacion_correo", asociacion.correo)
                        putString("asociacion_telefono", asociacion.telefono ?: "")
                        putString("asociacion_direccion", asociacion.direccion ?: "")
                        putString("asociacion_imagen", asociacion.imagenUrl ?: "")
                        putInt("id_asociacion", asociacion.id) // <--- GUARDAR EL ID
                        apply()
                    }
                    startActivity(Intent(this, InicioAsociacionActivity::class.java))
                    finish()
                }!= null -> {}

                usuarioDAO.validarUsuario(correo, contrasena)?.let { usuario ->
                    prefs.edit().apply {
                        putString("tipo", "usuario")
                        putString("usuario_nombre", usuario.nombre)
                        putString("usuario_correo", usuario.correo)
                        putString("usuario_telefono", usuario.telefono ?: "")
                        apply()
                    }
                    startActivity(Intent(this, PantallaUsuario::class.java))
                    finish()
                } != null -> {}

                else -> {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

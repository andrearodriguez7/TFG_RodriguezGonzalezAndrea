package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rescatame.dao.AsociacionDAO
import com.example.rescatame.dao.UsuarioDAO
import com.example.rescatame.model.Asociacion
import com.example.rescatame.model.Usuario

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre = findViewById<EditText>(R.id.etNombreUsuario)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val etRepetirContrasena = findViewById<EditText>(R.id.etRepetirContrasena)
        val rgTipoCuenta = findViewById<RadioGroup>(R.id.rgTipoCuenta)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val backButton = findViewById<ImageView>(R.id.backButton)

        val usuarioDAO = UsuarioDAO(this)
        val asociacionDAO = AsociacionDAO(this)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()
            val repetir = etRepetirContrasena.text.toString().trim()

            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || repetir.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contrasena != repetir) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val seleccion = rgTipoCuenta.checkedRadioButtonId

            if (seleccion == R.id.rbUsuario) {
                val nuevoUsuario = Usuario(0, nombre, correo, contrasena, "")
                val insertado = usuarioDAO.insertarUsuario(nuevoUsuario)
                if (insertado) {
                    Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, PantallaUsuario::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                }
            } else if (seleccion == R.id.rbAsociacion) {
                val nuevaAsociacion = Asociacion(0, nombre, correo, contrasena, "", "", "")
                val insertado = asociacionDAO.insertarAsociacion(nuevaAsociacion)
                if (insertado) {
                    Toast.makeText(this, "Asociación registrada", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, InicioAsociacionActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Error al registrar asociación", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Selecciona un tipo de cuenta", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            startActivity(Intent(this, IniciarSesion::class.java))
            finish()
        }
    }
}

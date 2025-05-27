package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UsuariosAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.usuariosadmin_layout)

        val tvToggleAgregar = findViewById<TextView>(R.id.tvToggleAgregar)
        val layoutAgregar = findViewById<LinearLayout>(R.id.layoutAgregarAnimal)

        val tvToggleEliminar = findViewById<TextView>(R.id.tvToggleEliminar)
        val layoutEliminar = findViewById<LinearLayout>(R.id.layoutEliminarAnimal)

        val tvToggleModificar = findViewById<TextView>(R.id.tvToggleModificar)
        val layoutModificar = findViewById<LinearLayout>(R.id.layoutModificarAnimal)

        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, AjustesAdmin::class.java)
            startActivity(intent)
            finish()
        }

        tvToggleAgregar.setOnClickListener {
            layoutAgregar.visibility =
                if (layoutAgregar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            tvToggleAgregar.text =
                if (layoutAgregar.visibility == View.VISIBLE) "Añadir Usuario ▲" else "Añadir Usuario ▼"
        }

        tvToggleEliminar.setOnClickListener {
            layoutEliminar.visibility =
                if (layoutEliminar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            tvToggleEliminar.text =
                if (layoutEliminar.visibility == View.VISIBLE) "Eliminar Usuario ▲" else "Eliminar Usuario ▼"
        }

        tvToggleModificar.setOnClickListener {
            layoutModificar.visibility =
                if (layoutModificar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            tvToggleModificar.text =
                if (layoutModificar.visibility == View.VISIBLE) "Modificar Usuario ▲" else "Modificar Usuario ▼"
        }
    }
}

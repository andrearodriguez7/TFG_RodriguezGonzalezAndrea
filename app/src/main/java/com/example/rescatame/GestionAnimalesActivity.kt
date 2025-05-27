package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GestionAnimalesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asociacioninformacion_layout)

        val tvToggleAgregar = findViewById<TextView>(R.id.tvToggleAgregar)
        val layoutAgregar = findViewById<LinearLayout>(R.id.layoutAgregarAnimal)

        val tvToggleEliminar = findViewById<TextView>(R.id.tvToggleEliminar)
        val layoutEliminar = findViewById<LinearLayout>(R.id.layoutEliminarAnimal)

        val tvToggleModificar = findViewById<TextView>(R.id.tvToggleModificar)
        val layoutModificar = findViewById<LinearLayout>(R.id.layoutModificarAnimal)

        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, InicioAsociacionActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvToggleAgregar.setOnClickListener {
            if (layoutAgregar.visibility == View.VISIBLE) {
                layoutAgregar.visibility = View.GONE
                tvToggleAgregar.text = "Añadir Animal ▼"
            } else {
                layoutAgregar.visibility = View.VISIBLE
                tvToggleAgregar.text = "Añadir Animal ▲"
            }
        }

        tvToggleEliminar.setOnClickListener {
            if (layoutEliminar.visibility == View.VISIBLE) {
                layoutEliminar.visibility = View.GONE
                tvToggleEliminar.text = "Eliminar Animal ▼"
            } else {
                layoutEliminar.visibility = View.VISIBLE
                tvToggleEliminar.text = "Eliminar Animal ▲"
            }
        }

        tvToggleModificar.setOnClickListener {
            if (layoutModificar.visibility == View.VISIBLE) {
                layoutModificar.visibility = View.GONE
                tvToggleModificar.text = "Modificar Animal ▼"
            } else {
                layoutModificar.visibility = View.VISIBLE
                tvToggleModificar.text = "Modificar Animal ▲"
            }
        }
    }
}

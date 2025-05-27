package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AsociacionesAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asociacionesadmin_layout)

        // Flecha de volver
        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, AjustesAdmin::class.java)
            startActivity(intent)
            finish()
        }

        val tvToggleAgregar = findViewById<TextView>(R.id.tvToggleAgregar)
        val layoutAgregar = findViewById<LinearLayout>(R.id.layoutAgregarAsociacion)

        val tvToggleEliminar = findViewById<TextView>(R.id.tvToggleEliminar)
        val layoutEliminar = findViewById<LinearLayout>(R.id.layoutEliminarAsociacion)

        val tvToggleModificar = findViewById<TextView>(R.id.tvToggleModificar)
        val layoutModificar = findViewById<LinearLayout>(R.id.layoutModificarAsociacion)

        tvToggleAgregar.setOnClickListener {
            layoutAgregar.visibility =
                if (layoutAgregar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            tvToggleAgregar.text =
                if (layoutAgregar.visibility == View.VISIBLE) "Añadir Asociación ▲" else "Añadir Asociación ▼"
        }

        tvToggleEliminar.setOnClickListener {
            layoutEliminar.visibility =
                if (layoutEliminar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            tvToggleEliminar.text =
                if (layoutEliminar.visibility == View.VISIBLE) "Eliminar Asociación ▲" else "Eliminar Asociación ▼"
        }

        tvToggleModificar.setOnClickListener {
            layoutModificar.visibility =
                if (layoutModificar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            tvToggleModificar.text =
                if (layoutModificar.visibility == View.VISIBLE) "Modificar Asociación ▲" else "Modificar Asociación ▼"
        }
    }
}

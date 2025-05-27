package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PerrosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perros_layout)

        val btnVolver = findViewById<ImageView>(R.id.btnVolver)
        val imageToto = findViewById<ImageView>(R.id.imageToto)

        // Acción del botón volver (flecha)
        btnVolver.setOnClickListener {
            val intent = Intent(this, InicioProtectora::class.java)
            startActivity(intent)
            finish()
        }

        // Acción al pulsar sobre la imagen de Totó
        imageToto.setOnClickListener {
            val intent = Intent(this, DetalleAnimalActivity::class.java)
            intent.putExtra("nombre", "Totó")
            intent.putExtra("edad", "14 años")
            intent.putExtra("tamano", "Pequeño")
            intent.putExtra("raza", "Yorkshire")
            intent.putExtra("sexo", "Macho")
            intent.putExtra(
                "descripcion",
                "Totó es un perro tranquilo y dulce que disfruta de los pequeños placeres de la vida. Le encanta comer y dar tranquilos paseos por la calle."
            )
            intent.putExtra(
                "salud",
                "Problemas renales: Totó está bajo tratamiento para su salud renal, por lo que requiere cuidados especiales y visitas regulares al veterinario."
            )
            intent.putExtra(
                "historial",
                "Totó fue abandonado por su anterior familia. Una voluntaria del refugio lo encontró. Ahora necesita un hogar donde pueda recibir el amor y la tranquilidad que merece."
            )
            intent.putExtra("imagen", R.drawable.animal_image)
            startActivity(intent)
        }
    }
}

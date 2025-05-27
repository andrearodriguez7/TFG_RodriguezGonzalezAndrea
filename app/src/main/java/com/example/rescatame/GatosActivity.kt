package com.example.rescatame

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class GatosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gatos_layout)

        val flechaAtras = findViewById<ImageView>(R.id.flechaAtras)

        flechaAtras.setOnClickListener {
            val intent = Intent(this, InicioProtectora::class.java)
            startActivity(intent)
            finish()
        }
    }
}

package com.example.rescatame.model

data class Usuario(
    val id: Int = 0,
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val telefono: String?
)

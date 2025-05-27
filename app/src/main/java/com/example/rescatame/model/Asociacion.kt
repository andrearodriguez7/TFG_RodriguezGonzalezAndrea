package com.example.rescatame.model

data class Asociacion(
    val id: Int = 0,
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val telefono: String,
    val direccion: String,
    val imagenUrl: String
)

package com.example.rescatame.model

data class Animal(
    val id: Int = 0,
    val nombre: String,
    val edad: String?,
    val tamano: String?,
    val raza: String?,
    val sexo: String?,
    val tipo: String, // "Perro" o "Gato"
    val descripcion: String?,
    val salud: String?,
    val historia: String?,
    val imagenUrl: String?,
    val idAsociacion: Int
)

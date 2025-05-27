package com.example.rescatame.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.rescatame.DatabaseHelper
import com.example.rescatame.model.Animal

class AnimalDAO(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertar(animal: Animal): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", animal.nombre)
            put("edad", animal.edad)
            put("tamano", animal.tamano)
            put("raza", animal.raza)
            put("sexo", animal.sexo)
            put("tipo", animal.tipo)
            put("descripcion", animal.descripcion)
            put("salud", animal.salud)
            put("historia", animal.historia)
            put("imagen_url", animal.imagenUrl)
            put("id_asociacion", animal.idAsociacion)
        }
        return db.insert("Animal", null, values)
    }

    fun obtenerTodos(): List<Animal> {
        val lista = mutableListOf<Animal>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Animal", null)
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursorToAnimal(cursor))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun obtenerPorAsociacion(idAsociacion: Int): List<Animal> {
        val lista = mutableListOf<Animal>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Animal WHERE id_asociacion = ?", arrayOf(idAsociacion.toString()))
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursorToAnimal(cursor))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun eliminarPorNombre(nombre: String): Int {
        val db = dbHelper.writableDatabase
        return db.delete("Animal", "nombre = ?", arrayOf(nombre))
    }

    fun modificar(animal: Animal): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("edad", animal.edad)
            put("tamano", animal.tamano)
            put("raza", animal.raza)
            put("sexo", animal.sexo)
            put("tipo", animal.tipo)
            put("descripcion", animal.descripcion)
            put("salud", animal.salud)
            put("historia", animal.historia)
            put("imagen_url", animal.imagenUrl)
            put("id_asociacion", animal.idAsociacion)
        }
        return db.update("Animal", values, "nombre = ?", arrayOf(animal.nombre))
    }

    private fun cursorToAnimal(cursor: android.database.Cursor): Animal {
        return Animal(
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
            nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
            edad = cursor.getString(cursor.getColumnIndexOrThrow("edad")),
            tamano = cursor.getString(cursor.getColumnIndexOrThrow("tamano")),
            raza = cursor.getString(cursor.getColumnIndexOrThrow("raza")),
            sexo = cursor.getString(cursor.getColumnIndexOrThrow("sexo")),
            tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo")),
            descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
            salud = cursor.getString(cursor.getColumnIndexOrThrow("salud")),
            historia = cursor.getString(cursor.getColumnIndexOrThrow("historia")),
            imagenUrl = cursor.getString(cursor.getColumnIndexOrThrow("imagen_url")),
            idAsociacion = cursor.getInt(cursor.getColumnIndexOrThrow("id_asociacion"))
        )
    }

    fun obtenerPorTipoYAsociacion(tipo: String, idAsociacion: Int): List<Animal> {
        val lista = mutableListOf<Animal>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM Animal WHERE tipo = ? AND id_asociacion = ?",
            arrayOf(tipo, idAsociacion.toString())
        )
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursorToAnimal(cursor))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

}

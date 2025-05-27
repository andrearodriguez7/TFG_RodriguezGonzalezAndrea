package com.example.rescatame.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.rescatame.DatabaseHelper
import com.example.rescatame.model.Asociacion

class AsociacionDAO(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertarAsociacion(asociacion: Asociacion): Boolean {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", asociacion.nombre)
            put("correo", asociacion.correo)
            put("contrasena", asociacion.contrasena)
            put("telefono", asociacion.telefono)
            put("direccion", asociacion.direccion)
            put("imagen_url", asociacion.imagenUrl)
        }

        val resultado = db.insert("Asociacion", null, values)
        db.close()
        return resultado != -1L
    }

    fun validarAsociacion(correo: String, contrasena: String): Asociacion? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM Asociacion WHERE correo = ? AND contrasena = ?",
            arrayOf(correo, contrasena)
        )

        var asociacion: Asociacion? = null
        if (cursor.moveToFirst()) {
            asociacion = Asociacion(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                correo = cursor.getString(cursor.getColumnIndexOrThrow("correo")),
                contrasena = cursor.getString(cursor.getColumnIndexOrThrow("contrasena")),
                telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono")),
                direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion")),
                imagenUrl = cursor.getString(cursor.getColumnIndexOrThrow("imagen_url"))
            )
        }

        cursor.close()
        db.close()
        return asociacion
    }
}

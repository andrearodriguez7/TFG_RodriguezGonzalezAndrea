package com.example.rescatame.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.rescatame.DatabaseHelper
import com.example.rescatame.model.Usuario

class UsuarioDAO(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun insertarUsuario(usuario: Usuario): Boolean {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", usuario.nombre)
            put("correo", usuario.correo)
            put("contrasena", usuario.contrasena)
            put("telefono", usuario.telefono)
        }

        val resultado = db.insert("Usuario", null, values)
        db.close()
        return resultado != -1L
    }

    fun validarUsuario(correo: String, contrasena: String): Usuario? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM Usuario WHERE correo = ? AND contrasena = ?",
            arrayOf(correo, contrasena)
        )

        var usuario: Usuario? = null
        if (cursor.moveToFirst()) {
            usuario = Usuario(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                correo = cursor.getString(cursor.getColumnIndexOrThrow("correo")),
                contrasena = cursor.getString(cursor.getColumnIndexOrThrow("contrasena")),
                telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"))
            )
        }

        cursor.close()
        db.close()
        return usuario
    }
}

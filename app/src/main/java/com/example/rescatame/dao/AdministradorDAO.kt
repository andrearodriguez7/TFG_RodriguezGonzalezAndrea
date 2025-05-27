package com.example.rescatame.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.rescatame.DatabaseHelper
import com.example.rescatame.model.Administrador

class AdministradorDAO(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun validarAdministrador(correo: String, contrasena: String): Administrador? {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM Administrador WHERE correo = ? AND contrasena = ?",
            arrayOf(correo, contrasena)
        )
        var administrador: Administrador? = null
        if (cursor.moveToFirst()) {
            administrador = Administrador(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                correo = cursor.getString(cursor.getColumnIndexOrThrow("correo")),
                contrasena = cursor.getString(cursor.getColumnIndexOrThrow("contrasena"))
            )
        }
        cursor.close()
        db.close()
        return administrador
    }
}

package com.example.rescatame

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USUARIO)
        db.execSQL(CREATE_TABLE_ASOCIACION)
        db.execSQL(CREATE_TABLE_ADMIN)
        db.execSQL(CREATE_TABLE_ANIMAL)

        // Insertar Administrador
        db.execSQL("INSERT INTO Administrador (nombre, correo, contrasena) VALUES ('Andrea', 'admin@rescatame.com', 'admin123');")

        // Insertar Asociación
        db.execSQL("""
            INSERT INTO Asociacion (nombre, correo, contrasena, telefono, direccion, imagen_url)
            VALUES ('Arca de Noé Sevilla', 'estrellaarcadenoe@yahoo.es', 'arca123', '+34 645 11 11 11', 'Sevilla', 'logo');
        """)

        // Insertar Animales (Perros)
        db.execSQL("INSERT INTO Animal VALUES (null, 'Luna', '3 años', 'Mediano', 'Labrador', 'Hembra', 'Perro', 'Muy cariñosa y activa', 'Buena salud', 'Rescatada de la calle', 'perro1', 1);")
        db.execSQL("INSERT INTO Animal VALUES (null, 'Chispas', '2 años', 'Pequeño', 'Beagle', 'Macho', 'Perro', 'Muy juguetón', 'Buena salud', 'Encontrado solo', 'perro2', 1);")
        db.execSQL("INSERT INTO Animal VALUES (null, 'Max', '5 años', 'Grande', 'Pastor Alemán', 'Macho', 'Perro', 'Protector y obediente', 'Buena salud', 'Rehabilitado', 'perro3', 1);")
        db.execSQL("INSERT INTO Animal VALUES (null, 'Ron', '4 años', 'Mediano', 'Golden Retriever', 'Macho', 'Perro', 'Tranquilo y amable', 'Buena salud', 'Abandonado por su dueño', 'perro4', 1);")
        db.execSQL("INSERT INTO Animal VALUES (null, 'Totó', '14 años', 'Pequeño', 'Yorkshire', 'Macho', 'Perro', 'Tranquilo y dulce', 'Problemas renales', 'Abandonado, ahora en recuperación', 'animal_image', 1);")
        db.execSQL("INSERT INTO Animal VALUES (null, 'Luna', '6 años', 'Mediano', 'Bodeguero', 'Hembra', 'Perro', 'Sociable', 'Buena salud', 'Recogida de una finca', 'perro5', 1);")

        // Insertar Animales (Gatos)
        db.execSQL("INSERT INTO Animal VALUES (null, 'Mochi', '1 año', 'Pequeño', 'Siamés', 'Macho', 'Gato', 'Muy sociable', 'Buena salud', 'Rescatado de un contenedor', 'gato1', 1);")
        db.execSQL("INSERT INTO Animal VALUES (null, 'Nube', '2 años', 'Mediano', 'Persa', 'Hembra', 'Gato', 'Duerme mucho', 'Buena salud', 'Rescatada', 'gato2', 1);")
        db.execSQL("INSERT INTO Animal VALUES (null, 'Canela', '4 años', 'Pequeño', 'Común europeo', 'Hembra', 'Gato', 'Juguetona', 'Buena salud', 'Abandonada en una caja', 'gato3', 1);")
        db.execSQL("INSERT INTO Animal VALUES (null, 'Simba', '3 años', 'Grande', 'Bengalí', 'Macho', 'Gato', 'Muy activo', 'Buena salud', 'Nacido en la asociación', 'gato4', 1);")
        db.execSQL("INSERT INTO Animal VALUES (null, 'Algodón', '2 años', 'Pequeño', 'Siamés', 'Macho', 'Gato', 'Tímido pero dulce', 'Buena salud', 'Recogido de la calle', 'gato5', 1);")
        db.execSQL("INSERT INTO Animal VALUES (null, 'Croqueta', '6 meses', 'Pequeño', 'Común europeo', 'Hembra', 'Gato', 'Muy pequeña y curiosa', 'Buena salud', 'Rescatada junto a hermanos', 'gato', 1);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Usuario")
        db.execSQL("DROP TABLE IF EXISTS Asociacion")
        db.execSQL("DROP TABLE IF EXISTS Administrador")
        db.execSQL("DROP TABLE IF EXISTS Animal")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "Rescatame.db"
        const val DATABASE_VERSION = 2  // ← Aquí actualizamos el número de versión

        private const val CREATE_TABLE_USUARIO = """
            CREATE TABLE IF NOT EXISTS Usuario (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                correo TEXT UNIQUE NOT NULL,
                contrasena TEXT NOT NULL,
                telefono TEXT
            );
        """

        private const val CREATE_TABLE_ASOCIACION = """
            CREATE TABLE IF NOT EXISTS Asociacion (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                correo TEXT UNIQUE NOT NULL,
                contrasena TEXT NOT NULL,
                telefono TEXT,
                direccion TEXT,
                imagen_url TEXT
            );
        """

        private const val CREATE_TABLE_ADMIN = """
            CREATE TABLE IF NOT EXISTS Administrador (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                correo TEXT UNIQUE NOT NULL,
                contrasena TEXT NOT NULL
            );
        """

        private const val CREATE_TABLE_ANIMAL = """
            CREATE TABLE IF NOT EXISTS Animal (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                edad TEXT,
                tamano TEXT,
                raza TEXT,
                sexo TEXT,
                tipo TEXT CHECK(tipo IN ('Perro', 'Gato')),
                descripcion TEXT,
                salud TEXT,
                historia TEXT,
                imagen_url TEXT,
                id_asociacion INTEGER,
                FOREIGN KEY (id_asociacion) REFERENCES Asociacion(id)
            );
        """
    }
}

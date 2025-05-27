package com.example.rescatame

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.rescatame.dao.AnimalDAO
import com.example.rescatame.model.Animal
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AnimalDAOTest {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var dao: AnimalDAO
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        dbHelper = DatabaseHelper(context)
        dao = AnimalDAO(context)

        val db = dbHelper.writableDatabase
        db.execSQL("DELETE FROM Animal")
        db.close()
    }

    @After
    fun tearDown() {
        dbHelper.close()
    }

    @Test
    fun insertarAnimalYObtenerTodos_funcionaCorrectamente() {
        val animal = crearAnimalEjemplo()
        dao.insertar(animal)

        val lista = dao.obtenerTodos()
        assertEquals(1, lista.size)
        assertEquals("Totó", lista[0].nombre)
    }

    @Test
    fun obtenerPorAsociacion_devuelveCorrecto() {
        val animal = crearAnimalEjemplo()
        dao.insertar(animal)

        val lista = dao.obtenerPorAsociacion(1)
        assertEquals(1, lista.size)
        assertEquals("Totó", lista[0].nombre)
    }

    @Test
    fun eliminarPorNombre_funciona() {
        dao.insertar(crearAnimalEjemplo())

        val eliminados = dao.eliminarPorNombre("Totó")
        assertEquals(1, eliminados)
    }

    @Test
    fun modificarAnimal_funcionaCorrectamente() {
        val animal = crearAnimalEjemplo()
        dao.insertar(animal)

        val animalModificado = animal.copy(edad = "5 años", raza = "Labrador")
        val filasModificadas = dao.modificar(animalModificado)

        val lista = dao.obtenerTodos()
        assertEquals(1, filasModificadas)
        assertEquals("5 años", lista[0].edad)
        assertEquals("Labrador", lista[0].raza)
    }

    private fun crearAnimalEjemplo(): Animal {
        return Animal(
            id = 1,
            nombre = "Totó",
            edad = "2 años",
            tamano = "Pequeño",
            raza = "Mestizo",
            sexo = "Macho",
            tipo = "Perro",
            descripcion = "Tranquilo y juguetón",
            salud = "Vacunado y desparasitado",
            historia = "Encontrado en la calle",
            imagenUrl = "url_ejemplo",
            idAsociacion = 1
        )
    }
}

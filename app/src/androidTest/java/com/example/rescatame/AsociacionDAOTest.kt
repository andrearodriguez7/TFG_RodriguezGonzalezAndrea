package com.example.rescatame

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.rescatame.dao.AsociacionDAO
import com.example.rescatame.model.Asociacion
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AsociacionDAOTest {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var dao: AsociacionDAO
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        dbHelper = DatabaseHelper(context)
        dao = AsociacionDAO(context)

        val db = dbHelper.writableDatabase
        db.execSQL("DELETE FROM Asociacion")
        db.execSQL("INSERT INTO Asociacion (id, nombre, correo, contrasena, telefono, direccion, imagen_url) " +
                "VALUES (1, 'ARCA', 'arca@email.com', 'abcd1234', '666666666', 'Calle Luna', 'url')")
        db.close()
    }

    @After
    fun tearDown() {
        dbHelper.close()
    }

    @Test
    fun insertarAsociacion_valida_devuelveTrue() {
        val asociacion = Asociacion(2, "Gatos Sevilla", "gatos@email.com", "1234", "654321098", "Av. Gato", "img_url")
        val resultado = dao.insertarAsociacion(asociacion)
        assertTrue(resultado)
    }

    @Test
    fun validarAsociacion_correcta_devuelveObjeto() {
        val asociacion = dao.validarAsociacion("arca@email.com", "abcd1234")
        assertNotNull(asociacion)
        assertEquals("ARCA", asociacion?.nombre)
    }

    @Test
    fun validarAsociacion_credencialesIncorrectas_devuelveNull() {
        val asociacion = dao.validarAsociacion("wrong@email.com", "mal")
        assertNull(asociacion)
    }

    @Test
    fun validarAsociacion_conCorreoVacio_devuelveNull() {
        val asociacion = dao.validarAsociacion("", "abcd1234")
        assertNull(asociacion)
    }

    @Test
    fun validarAsociacion_conContrasenaVacia_devuelveNull() {
        val asociacion = dao.validarAsociacion("arca@email.com", "")
        assertNull(asociacion)
    }
}

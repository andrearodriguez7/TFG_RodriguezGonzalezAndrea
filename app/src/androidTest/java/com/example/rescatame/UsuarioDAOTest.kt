package com.example.rescatame

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.rescatame.dao.UsuarioDAO
import com.example.rescatame.model.Usuario
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UsuarioDAOTest {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var dao: UsuarioDAO
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        dbHelper = DatabaseHelper(context)
        dao = UsuarioDAO(context)

        val db = dbHelper.writableDatabase
        db.execSQL("DELETE FROM Usuario")
        db.execSQL("INSERT INTO Usuario (id, nombre, correo, contrasena, telefono) VALUES (1, 'Andrea', 'andrea@email.com', '1234', '666666666')")
        db.close()
    }

    @After
    fun tearDown() {
        dbHelper.close()
    }

    @Test
    fun insertarUsuario_valido_devuelveTrue() {
        val usuario = Usuario(2, "Carlos", "carlos@email.com", "abcd", "654321000")
        val resultado = dao.insertarUsuario(usuario)
        assertTrue(resultado)
    }

    @Test
    fun validarUsuario_credencialesCorrectas_devuelveUsuario() {
        val usuario = dao.validarUsuario("andrea@email.com", "1234")
        assertNotNull(usuario)
        assertEquals("Andrea", usuario?.nombre)
    }

    @Test
    fun validarUsuario_credencialesIncorrectas_devuelveNull() {
        val usuario = dao.validarUsuario("noexiste@email.com", "0000")
        assertNull(usuario)
    }

    @Test
    fun validarUsuario_correoVacio_devuelveNull() {
        val usuario = dao.validarUsuario("", "1234")
        assertNull(usuario)
    }

    @Test
    fun validarUsuario_contrasenaVacia_devuelveNull() {
        val usuario = dao.validarUsuario("andrea@email.com", "")
        assertNull(usuario)
    }
}

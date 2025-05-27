package com.example.rescatame

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.rescatame.dao.AdministradorDAO
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AdministradorDAOTest {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var dao: AdministradorDAO
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        dbHelper = DatabaseHelper(context)
        dao = AdministradorDAO(context)

        val db = dbHelper.writableDatabase
        db.execSQL("DELETE FROM Administrador")
        db.execSQL("INSERT INTO Administrador (id, nombre, correo, contrasena) " +
                "VALUES (1, 'Admin', 'admin@email.com', '1234')")
        db.close()
    }

    @After
    fun tearDown() {
        dbHelper.close()
    }

    @Test
    fun loginAdministradorCorrecto_devuelveAdministrador() {
        val admin = dao.validarAdministrador("admin@email.com", "1234")
        assertNotNull(admin)
        assertEquals("Admin", admin?.nombre)
    }

    @Test
    fun loginAdministradorIncorrecto_devuelveNull() {
        val admin = dao.validarAdministrador("fake@email.com", "wrong")
        assertNull(admin)
    }

    @Test
    fun loginConCorreoVacio_devuelveNull() {
        val admin = dao.validarAdministrador("", "1234")
        assertNull(admin)
    }

    @Test
    fun loginConContrasenaVacia_devuelveNull() {
        val admin = dao.validarAdministrador("admin@email.com", "")
        assertNull(admin)
    }

    @Test
    fun loginConCorreoLargo_devuelveNull() {
        val correoLargo = "a".repeat(300) + "@email.com"
        val admin = dao.validarAdministrador(correoLargo, "1234")
        assertNull(admin)
    }
}

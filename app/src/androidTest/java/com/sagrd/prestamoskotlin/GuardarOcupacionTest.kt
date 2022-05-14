package com.sagrd.prestamoskotlin

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sagrd.prestamoskotlin.data.OcupacionDao
import com.sagrd.prestamoskotlin.data.OcupacionRepository
import com.sagrd.prestamoskotlin.data.PrestamosDb
import com.sagrd.prestamoskotlin.model.Ocupacion
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GuardarOcupacionTest {

    private lateinit var db: PrestamosDb
    private lateinit var dao: OcupacionDao

    private lateinit var ocupacionRepository: OcupacionRepository

    @Before
    fun onBefore(){
        val context =      InstrumentationRegistry.getInstrumentation().targetContext


        db = Room.inMemoryDatabaseBuilder(context, PrestamosDb::class.java).build()
        dao = db.ocupacionDao

        ocupacionRepository= OcupacionRepository(dao)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun Guardar_Ocupacion() = runBlocking {

        val ocupacion = Ocupacion(0,"ingeniero",0f)

        //insertar con el repo
        ocupacionRepository.Insertar( ocupacion)

        val ocupaciones = ocupacionRepository.GetLista().first().count()

        assertTrue(ocupaciones>0)
    }
}
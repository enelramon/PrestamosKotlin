package com.sagrd.prestamoskotlin.data

import com.sagrd.prestamoskotlin.model.Ocupacion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OcupacionRepository @Inject constructor(
    val ocupacionDao: OcupacionDao
) {
    suspend fun Insertar(ocupacion: Ocupacion)= ocupacionDao.Insertar(ocupacion)

    fun Buscar(ocupacionId: Int): Flow<Ocupacion>   =  ocupacionDao.Buscar(ocupacionId)

    suspend fun Eliminar(ocupacion: Ocupacion)= ocupacionDao.Eliminar(ocupacion)

    fun GetLista(): Flow<List<Ocupacion>> = ocupacionDao.GetLista()
}
package com.sagrd.prestamoskotlin.views.ocupaciones


import androidx.lifecycle.*
import com.sagrd.prestamoskotlin.data.OcupacionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import com.sagrd.prestamoskotlin.model.Ocupacion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    val ocupacionDao: OcupacionDao
) : ViewModel(){

    val ocupaciones : Flow<List<Ocupacion>>
        get() =  ocupacionDao.GetLista()

    private val _guardado = MutableLiveData(false)
    val guardado: LiveData<Boolean> get() = _guardado

    fun guardar(ocupacion: Ocupacion){
        viewModelScope.launch {
            ocupacionDao.Insertar(ocupacion)
            _guardado.value=true
        }
    }

}
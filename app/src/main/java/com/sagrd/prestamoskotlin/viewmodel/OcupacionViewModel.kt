package com.sagrd.prestamoskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sagrd.prestamoskotlin.data.OcupacionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import com.sagrd.prestamoskotlin.model.Ocupacion
import com.sagrd.prestamoskotlin.views.ocupaciones.OcupacionViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    val ocupacionDao: OcupacionDao
) : ViewModel(){

    //
    private  val _viewState = MutableStateFlow(OcupacionViewState())
    val viewState: StateFlow<OcupacionViewState>
        get() = _viewState

    fun OcupacionClickedGuardar(descripcion: String,ingreso:Float){
        viewModelScope.launch {
            _viewState.value = OcupacionViewState(true,null)
            guardar(Ocupacion(0,descripcion, ingreso))
            delay(2000)
            _viewState.value = OcupacionViewState(false,"Test Error")
        }
    }

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
package com.sagrd.prestamoskotlin.views.ocupaciones


import androidx.lifecycle.*
import com.sagrd.prestamoskotlin.data.OcupacionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import com.sagrd.prestamoskotlin.model.Ocupacion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    val repository: OcupacionRepository
) : ViewModel(){

    val ocupaciones : Flow<List<Ocupacion>>
        get() =  repository.GetLista()

    private val _guardado = MutableLiveData(false)
    val guardado: LiveData<Boolean> get() = _guardado

    fun guardar(ocupacion: Ocupacion){
        viewModelScope.launch {
            repository.Insertar(ocupacion)
            _guardado.value=true
        }
    }

}
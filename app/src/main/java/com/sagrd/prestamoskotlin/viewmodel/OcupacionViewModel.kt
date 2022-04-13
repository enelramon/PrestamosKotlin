package com.sagrd.prestamoskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sagrd.prestamoskotlin.data.OcupacionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    val ocupacionDao: OcupacionDao
) : ViewModel(){


}
package com.sagrd.prestamoskotlin.views.ocupaciones

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sagrd.prestamoskotlin.R

class ListaOcupacionesFragment : Fragment() {

    companion object {
        fun newInstance() = ListaOcupacionesFragment()
    }

    private lateinit var viewModel: ListaOcupacionesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_ocupaciones_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListaOcupacionesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
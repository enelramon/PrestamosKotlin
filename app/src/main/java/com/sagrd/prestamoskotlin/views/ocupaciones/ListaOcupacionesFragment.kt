package com.sagrd.prestamoskotlin.views.ocupaciones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.sagrd.prestamoskotlin.R
import com.sagrd.prestamoskotlin.databinding.ListaOcupacionesFragmentBinding
import com.sagrd.prestamoskotlin.model.Ocupacion
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListaOcupacionesFragment : Fragment() {

    private val viewModel: OcupacionViewModel by viewModels()
    private lateinit var binding: ListaOcupacionesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListaOcupacionesFragmentBinding.inflate(inflater, container, false)

        val adapter = OcupacionesAdapter(::openOcupacionesFragment)

        binding.ocupacionesRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.ocupaciones.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { lista ->
                    adapter.submitList(lista)
                }
        }

        binding.agregarButton.setOnClickListener {
            openOcupacionesFragment()
        }

        return binding.root
    }

    fun openOcupacionesFragment(ocupacion: Ocupacion?=null)  {
        val action = ListaOcupacionesFragmentDirections.actionToOcupacionesFragment(ocupacion)
       findNavController().navigate(action)
    }

}
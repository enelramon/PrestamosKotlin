package com.sagrd.prestamoskotlin.views.ocupaciones

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.sagrd.prestamoskotlin.R
import com.sagrd.prestamoskotlin.databinding.ListaOcupacionesFragmentBinding
import com.sagrd.prestamoskotlin.databinding.OcupacionesFragmentBinding
import com.sagrd.prestamoskotlin.model.Ocupacion
import com.sagrd.prestamoskotlin.viewmodel.OcupacionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OcupacionesFragment : Fragment() {

    private val viewModel: OcupacionViewModel by viewModels()
    private lateinit var binding: OcupacionesFragmentBinding

    // get the arguments
    private val args : OcupacionesFragmentArgs by navArgs()

    private var ocupacionId: Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OcupacionesFragmentBinding.inflate(inflater, container, false)

       LlenarCampos()

        binding.guardarButton.setOnClickListener {
            viewModel.OcupacionClickedGuardar(
                binding.ocupacionEditText.text.toString(),
                binding.ingresosEditText.floatValue()
            )
        }

        lifecycleScope.launch{
            //la funcion repeatOnLifecycle es la que lanza la sucripcion a los evento cuando inicia la acion
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                //llamada a una funcion en una sobre carga
                viewModel.viewState.collect(::render)
            }
        }

        viewModel.guardado.observe(viewLifecycleOwner){
                if (it) {
                    Snackbar.make(binding.ingresosEditText, "Guardo", Snackbar.LENGTH_LONG).show()
                     findNavController().navigateUp()
                }
        }

        return binding.root
    }

    private fun render(viewState: OcupacionViewState) = with(binding) {
        if (viewState.loading == true)
            loading.visibility = View.VISIBLE
        else
            loading.visibility = View.INVISIBLE
    }


    fun LlenarCampos(){
        val ocupacion: Ocupacion? = args.ocupacion

        ocupacion?.let {
            ocupacionId = it.ocupacionId
            binding.ocupacionEditText.setText(it.descripcion)
            binding.ingresosEditText.setText(it.Ingresos.toString())
        }
    }

    fun TextInputEditText.floatValue() = text.toString().toFloatOrNull() ?: 0.0f


}
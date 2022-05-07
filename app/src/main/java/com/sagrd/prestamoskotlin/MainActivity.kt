package com.sagrd.prestamoskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.sagrd.prestamoskotlin.databinding.ActivityMainBinding
import com.sagrd.prestamoskotlin.model.Ocupacion
import com.sagrd.prestamoskotlin.viewmodel.OcupacionViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: OcupacionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.guardarButton.setOnClickListener {
            viewModel.guardar(Ocupacion(
                0,
                binding.ocupacionEditText.text.toString(),
                binding.ingresosEditText.floatValue()
            ))
        }

        viewModel.guardado.observe(this) {
            if (it) {
                Snackbar.make(binding.ingresosEditText, "Guardo", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun TextInputEditText.floatValue() = text.toString().toFloatOrNull() ?: 0.0f

}
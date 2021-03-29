package com.example.currencyconsverterprep

import android.graphics.Color
import android.os.Build.VERSION_CODES.M
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.currencyconsverterprep.databinding.ActivityMainBinding
import com.example.currencyconsverterprep.main.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var  binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString(),

            )
        }



        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event){
                    is MainViewModel.CurrencyEvent.Success -> {

                        binding.apply {
                        progressBar.isVisible = false
                        tvResult.setTextColor(Color.WHITE)
                       tvResult.text = event.resultText




                        }
                    }
                    is MainViewModel.CurrencyEvent.Failure -> {
                        binding.apply {

                            progressBar.isVisible = false
                            tvResult.setTextColor(Color.WHITE)
                            tvResult.text = event.errorText
                        }
                    }
                    is MainViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true

                    }

                }

            }
        }
    }
}
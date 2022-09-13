package com.example.room.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.room.App
import com.example.room.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels{ object :ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val wordDao=(activity?.application as App).db.wordDao()
            return MainViewModel(wordDao) as T
        }
    } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(inflater)

        binding.btnPush.setOnClickListener {
            viewModel.addWord(binding.input.text.toString())
        }

        binding.btnClear.setOnClickListener {
            viewModel.clearAll()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.allWord.collect{
                binding.rezult.text=it.joinToString(separator = "\r\n")
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect{state->
                when(state){
                    is State.Error -> {
                        Snackbar.make(requireView(),state.error,Snackbar.LENGTH_LONG).show()
                        binding.btnPush.isEnabled=true
                        binding.btnClear.isEnabled=true
                        binding.inputLayout.error=state.error
                    }
                    State.Loading -> {
                        binding.btnPush.isEnabled=false
                        binding.btnClear.isEnabled=false
                        binding.inputLayout.error=null
                    }
                    State.Success -> {
                        binding.btnPush.isEnabled=true
                        binding.btnClear.isEnabled=true
                        binding.inputLayout.error=null
                    }
                }
            }
        }


        return binding.root
    }


}
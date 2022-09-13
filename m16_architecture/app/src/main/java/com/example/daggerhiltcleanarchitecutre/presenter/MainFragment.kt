package com.example.daggerhiltcleanarchitecutre.presenter

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.daggerhiltcleanarchitecutre.R
import com.example.daggerhiltcleanarchitecutre.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var mainViewModelFactory:MainViewModelFactory

    private val viewModel: MainViewModel by viewModels{ mainViewModelFactory }
    lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(inflater)
        binding.btnRefresh.setOnClickListener {
            viewModel.reloadUsefulActivity()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.state.collect{state->
                when(state){
                    is State.Error -> {
                       binding.message.text=state.message
                        binding.progress.isVisible=false
                        binding.btnRefresh.isEnabled=true
                    }
                    State.Loading -> {
                        binding.progress.isVisible=true
                        binding.btnRefresh.isEnabled=false
                    }
                    is State.Success -> {
                       binding.message.text=state.usefulActivity?.activity
                        binding.progress.isVisible=false
                        binding.btnRefresh.isEnabled=true
                    }
                }
            }
        }



        return binding.root
    }

}
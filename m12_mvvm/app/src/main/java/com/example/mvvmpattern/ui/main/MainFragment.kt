package com.example.mvvmpattern.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvmpattern.R
import com.example.mvvmpattern.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels {MainViewModelFactory()}
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(inflater)

        binding.btnSerach.setOnClickListener {
            val message=binding.serach.text.toString()
            viewModel.checkSearch(message)
        }


        viewModel.checkTextSize(binding.serach.text?.length ?: 0)
        binding.serach.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.checkTextSize(p0?.length ?: 0)
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect{ state->
                when(state){
                    is State.Error -> {
                        binding.progress.isVisible=false
                        binding.btnSerach.isEnabled=false
                        binding.serachLayout.error=state.error
                    }
                    State.Loading ->{
                        binding.progress.isVisible=true
                        binding.btnSerach.isEnabled=false
                        binding.serachLayout.error=null
                    }
                    State.Success -> {
                        binding.progress.isVisible=false
                        binding.btnSerach.isEnabled=true
                        binding.serachLayout.error=null
                    }
                }
            }

        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted{
            viewModel.search.collect{
                binding.message.text=it
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


}
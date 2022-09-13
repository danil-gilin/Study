package com.example.mvvmretrofit.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.mvvmretrofit.R
import com.example.mvvmretrofit.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    lateinit var binding:FragmentMainBinding
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentMainBinding.inflate(inflater)

        viewModel.getHuman()

        binding.update.setOnClickListener {
            viewModel.getHuman()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect{state->
                when(state){
                    is State.Error -> {
                        Snackbar.make(requireView(),state.error,Snackbar.LENGTH_LONG).show()
                        binding.progress.isVisible=false
                        binding.update.isEnabled=true
                    }
                    State.Loading -> {
                        binding.progress.isVisible=true
                        binding.update.isEnabled=false
                    }
                    is State.Success -> {
                        binding.name.text="Name :"+state.human.results.first().name.first
                        binding.lastName.text="LastName :"+state.human.results.first().name.last
                        binding.city.text="City :"+state.human.results.first().location.city
                        binding.country.text="Country :"+state.human.results.first().location.country
                        binding.gender.text="Gender :"+state.human.results.first().gender
                        binding.img.load(state.human.results.first().picture.large,this@MainFragment)
                        binding.progress.isVisible=false
                        binding.update.isEnabled=true
                    }
                }
            }
        }


        return binding.root
    }

    fun ImageView.load(url:String,fragment: Fragment ){
        Glide.with(fragment).load(url).centerCrop().into(this)
    }

}
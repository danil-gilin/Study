package com.example.premission.presenter.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.premission.R
import com.example.premission.databinding.FragmentMainBinding

import com.example.premission.presenter.main.rcViewPhoto.PhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var mainFactory: MainViewModelFactory

    private val viewModel: MainViewModel by viewModels { mainFactory }
    lateinit var binding: FragmentMainBinding
    private val adapter=PhotoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentMainBinding.inflate(inflater)

        binding.rcPhoto.adapter=adapter
        binding.rcPhoto.layoutManager=GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)

        viewModel.getPhoto()

        viewModel.photo.value?.size?.let { adapter.notifyItemRangeChanged(0, it) }
        viewModel.photo.observe(viewLifecycleOwner){
            adapter.submitList(it)
            binding.progressBar.visibility=View.GONE
        }

        binding.btnTakePhoto.setOnClickListener {
         findNavController().navigate(R.id.action_mainFragment_to_photoFragment)
        }

        binding.btnMap.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mapsFragment)
        }

        binding.viewModel=viewModel
        binding.lifecycleOwner=viewLifecycleOwner


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect{
                when(it){
                    is State.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    State.Loading -> {
                    }
                    State.Success -> {
                        Toast.makeText(requireContext(), "Список загружен", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }




        return binding.root
    }


}
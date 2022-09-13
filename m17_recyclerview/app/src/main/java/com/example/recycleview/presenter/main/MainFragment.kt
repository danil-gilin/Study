package com.example.recycleview.presenter.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recycleview.R
import com.example.recycleview.data.MarsRepository
import com.example.recycleview.databinding.FragmentMainBinding
import com.example.recycleview.entity.MarsPhoto.Photo
import com.example.recycleview.presenter.MarsPhotoAdapter
import com.example.recycleview.presenter.fullInfo.FullInfoFragment

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val adapter= MarsPhotoAdapter{photo,extra->onClickPhoto(photo,extra)}


    val viewModel: MainViewModel by viewModels{mainViewModelFactory}
    lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.rcMars.adapter=adapter.withLoadStateFooter(LaodStateAdapter())
        binding.rcMars.layoutManager=GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)

        viewModel.pageMarsPhoto.onEach {
           adapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.loadStateFlow.onEach {
            binding.swipe.isRefreshing=it.refresh==LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.swipe.setOnRefreshListener {
            adapter.refresh()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    fun onClickPhoto(item:Photo,extras: FragmentNavigator.Extras){
        val bundle = Bundle()
        bundle.putString(getString(R.string.main_to_full_Img),item.imgSrc)
        bundle.putString(getString(R.string.main_to_full_name),item.earthDate)
        findNavController().navigate(R.id.action_mainFragment_to_fullInfoFragment, args = bundle,null, navigatorExtras = extras)
    }
}
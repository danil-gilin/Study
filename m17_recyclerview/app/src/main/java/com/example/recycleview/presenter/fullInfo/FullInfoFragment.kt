package com.example.recycleview.presenter.fullInfo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.recycleview.R
import com.example.recycleview.databinding.FragmentFullInfoBinding
import java.util.*


class FullInfoFragment : Fragment() {

    lateinit var binding: FragmentFullInfoBinding
     var img_url:String=""
    var name=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            img_url= it.getString(getString(R.string.main_to_full_Img)).toString()
            name=it.getString(getString(R.string.main_to_full_name)).toString()
        }
        sharedElementEnterTransition=TransitionInflater.from(requireContext()).inflateTransition(R.transition.grid_transition)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentFullInfoBinding.inflate(inflater)

        binding.name.text=name
        binding.name.alpha=0.0F
        binding.name.animate().apply {
            duration=1500
            alpha(1.0F)
            start()
        }


      Glide.with(binding.imgFull.context)
            .load(img_url)
            .into(binding.imgFull)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FullInfoFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
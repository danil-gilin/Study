package com.example.skillbox_hw_quiz.Rezult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentQuizBinding
import com.example.skillbox_hw_quiz.databinding.FragmentRezultBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class RezultFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var rezult: ArrayList<String> = arrayListOf()
    private var _binding : FragmentRezultBinding?=null
    private val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            rezult= it.getStringArrayList("rezult") as ArrayList<String>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRezultBinding.inflate(inflater)

        binding.txt1.text=rezult[0]
        binding.txt2.text=rezult[1]
        binding.txt3.text=rezult[2]

        binding.reload.setOnClickListener {
            findNavController().navigate(R.id.action_rezultFragment_to_quizFragment)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RezultFragment().apply {
            }
    }
}
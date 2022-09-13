package com.example.skillbox_hw_quiz.quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentQuizBinding
import java.util.*
import kotlin.collections.ArrayList

class QuizFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var rezult: ArrayList<String> = arrayListOf()

    private var _binding : FragmentQuizBinding?=null
    private val binding get()= _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater)
        var quiz=QuizStorage.getQuiz(QuizStorage.Locale.En)
        when(Locale.getDefault().language){
            "ru" ->  quiz=QuizStorage.getQuiz(QuizStorage.Locale.Ru)
            "en" ->  quiz=QuizStorage.getQuiz(QuizStorage.Locale.En)
        }

        binding.questiongroup1.changeText(quiz.questions[0])
        binding.questiongroup2.changeText(quiz.questions[1])
        binding.questiongroup3.changeText(quiz.questions[2])

        binding.questiongroup1.setCheckListinetRezult()
        binding.questiongroup2.setCheckListinetRezult()
        binding.questiongroup3.setCheckListinetRezult()



        binding.push.setOnClickListener {
            rezult.add(binding.questiongroup1.rez)
            rezult.add(binding.questiongroup2.rez)
            rezult.add(binding.questiongroup3.rez)
            val bundle = Bundle()
            bundle.putStringArrayList("rezult",rezult);
            findNavController().navigate(R.id.action_quizFragment_to_rezultFragment,args=bundle)
        }

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_quizFragment_to_mainFragment)
        }




        return binding.root
    }



    companion object {
        @JvmStatic
        fun newInstance() = QuizFragment().apply {}
    }
}
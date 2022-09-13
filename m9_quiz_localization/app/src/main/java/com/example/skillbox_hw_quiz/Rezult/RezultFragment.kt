package com.example.skillbox_hw_quiz.Rezult

import android.animation.*
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
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
    private val leftToRight =PropertyValuesHolder.ofFloat(View.TRANSLATION_X,-200f,0f)
    private val alpha =PropertyValuesHolder.ofFloat(View.ALPHA,0f,1f)

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
        rezlutAnim(binding.txt1)
        rezlutAnim(binding.txt2)
        rezlutAnim(binding.txt3)


        ValueAnimator.ofObject(GradientArgbEvaluator,
            intArrayOf(Color.MAGENTA,Color.MAGENTA,Color.MAGENTA),
            intArrayOf(Color.MAGENTA,Color.MAGENTA,Color.BLUE),
            intArrayOf(Color.MAGENTA,Color.BLUE,Color.BLACK),
            intArrayOf(Color.BLUE,Color.BLACK,Color.GREEN),
            intArrayOf(Color.BLACK,Color.GREEN,Color.GREEN),
            intArrayOf(Color.GREEN,Color.GREEN,Color.GREEN),
            intArrayOf(Color.GREEN,Color.GREEN,Color.MAGENTA),
            ).apply {
                repeatMode =ObjectAnimator.REVERSE
                repeatCount=ObjectAnimator.INFINITE
                duration=3000
            addUpdateListener {
                val textShader=LinearGradient(
                    0f,0f,
                    binding.preview.paint.measureText(binding.preview.text.toString()),
                    binding.preview.textSize,
                    it.animatedValue as IntArray,
                    null,
                    Shader.TileMode.CLAMP
                )
                binding.preview.paint.shader=textShader
                binding.preview.invalidate()
            }
            start()
            }



        binding.reload.setOnClickListener {
            findNavController().navigate(R.id.action_rezultFragment_to_quizFragment)
        }

        return binding.root
    }

    fun rezlutAnim(text:TextView){
        text.alpha=0f
        ObjectAnimator.ofPropertyValuesHolder(text,leftToRight,alpha).apply{
            duration=1500
            interpolator=AccelerateInterpolator()
            start()
        }
    }

    object GradientArgbEvaluator :TypeEvaluator<IntArray>{
        val argbEvaluator =ArgbEvaluator()
        override fun evaluate(fraction: Float, start: IntArray, end: IntArray): IntArray {
           return start.mapIndexed { index, i ->
               argbEvaluator.evaluate(fraction,i,end[index] )as Int}.toIntArray()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RezultFragment().apply {
            }
    }
}
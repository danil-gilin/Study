package com.example.skillbox_hw_quiz.quiz

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.skillbox_hw_quiz.databinding.QuizCustomBinding

class CustomViewQuiz @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0
): LinearLayout(context, attrs, defStyleAttr){

    lateinit var questoinmain:Question
    var rez="Нет ответа"
    val binding= QuizCustomBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun changeText(questoin:Question){
        questoinmain=questoin
        binding.answer1.text=questoin.answers[0]
        binding.answer2.text=questoin.answers[1]
        binding.answer3.text=questoin.answers[2]
        binding.answer4.text=questoin.answers[3]
        binding.question.text=questoin.question
    }

    fun setCheckListinetRezult(){
        binding.chipGroup.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                binding.answer1.id->rez= questoinmain.feedback[0]
                binding.answer2.id->rez= questoinmain.feedback[1]
                binding.answer3.id->rez= questoinmain.feedback[2]
                binding.answer4.id->rez= questoinmain.feedback[3]
                else->rez="Нет ответа"
            }
        }
    }
}

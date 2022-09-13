package com.example.skill_blyat

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.skill_blyat.databinding.MassegaCustomBinding


class MassegaCustom @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?=null,
    defStyleAttr: Int=0
): LinearLayout(context, attrs, defStyleAttr){

    val binding= MassegaCustomBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun changeTopText(text:String){
        binding.top.text=text
    }

    fun changeBottomText(text:String){
        binding.bottom.text=text
    }

    fun changewidth(width:Int){
        binding.grey.width=width
    }
}
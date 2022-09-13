package com.example.skill_blyat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skill_blyat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customview.changeTopText("верхняя строчка, настроенная из кода")
        binding.customview.changeBottomText("нижняя строчка, настроенная из кода")
    }
}
package com.example.dz_11_skilbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dz_11_skilbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository=Repository(this)

        binding.textView.text=repository.getText()


        binding.btnClear.setOnClickListener {
            repository.clearText()
            binding.textView.text=repository.getText()
        }

        binding.btnSave.setOnClickListener {
            repository.saveText(binding.editText.text.toString())
            binding.textView.text=repository.getText()
            binding.editText.text?.clear()
        }

    }
}
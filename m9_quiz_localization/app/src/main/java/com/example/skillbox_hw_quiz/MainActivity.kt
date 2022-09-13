package com.example.skillbox_hw_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.skillbox_hw_quiz.databinding.MainActivityBinding
import com.example.skillbox_hw_quiz.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding :MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment=
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_nav) as NavHostFragment
        
        val navController = navHostFragment.navController


    }
}
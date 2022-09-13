package com.example.skill3_dz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.result.ActivityResultLauncher
import com.example.skill3_dz.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    lateinit var binding: ActivityMainBinding
    var progressFlag=true
    var jon =Job()
    var first= CoroutineScope(jon+Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seekBar.setOnSeekBarChangeListener(this)


        binding.btnStart.setOnClickListener {
            binding.seekBar.isEnabled=false
            binding.btnStart.text="STOP"
            if (progressFlag) {
                progressFlag=false
                first.launch {
                    val progress = binding.Proggress.text.toString().toInt()
                    var progresstext = binding.Proggress.text.toString().toInt()
                    binding.progressBar.max = progress
                    binding.progressBar.progress=progress
                    repeat(progress) {
                        binding.progressBar.progress--
                        progresstext--
                        binding.Proggress.text = progresstext.toString()
                        delay(1000)
                    }
                    binding.seekBar.isEnabled = true
                    binding.seekBar.progress = 10
                    binding.progressBar.progress=binding.progressBar.max
                    binding.btnStart.text = "START"
                    progressFlag = true
                    binding.Proggress.text = 10.toString()
                    this.cancel()
                }
            }else{
                first.coroutineContext.cancelChildren()
                binding.seekBar.isEnabled = true
                binding.seekBar.progress = 10
                binding.progressBar.progress=binding.progressBar.max
                binding.btnStart.text = "START"
                progressFlag = true
                binding.Proggress.text = 10.toString()
            }
        }

    }


    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        var progress=p1/10
        progress *= 10
        binding.Proggress.text=progress.toString()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }
}
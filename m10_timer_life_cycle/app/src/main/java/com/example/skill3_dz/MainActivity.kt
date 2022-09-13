package com.example.skill3_dz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.skill3_dz.databinding.ActivityMainBinding
import kotlinx.coroutines.*


private const val KEY_TIME="Key_time"
private const val KEY_MAX_PROGRESS="Key_max_progress"
private const val KEY_THREAD="Key_thread"

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {
    lateinit var binding: ActivityMainBinding
    var progressFlag=true
    var takeProggresFlag=true
    var progresstimer=0
    var jon =Job()
    var progressmax =0

    var takeProggres=0
    var first= CoroutineScope(jon+Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState.let {
            if (it != null) {
                takeProggres=(it.getInt(KEY_TIME))
                progressFlag=it.getBoolean(KEY_THREAD)
                binding.progressBar.max=it.getInt(KEY_MAX_PROGRESS)
                progressmax=binding.progressBar.max
            }
        }

        if(!progressFlag){
            binding.seekBar.isEnabled=false
            binding.btnStart.text="STOP"
            binding.Proggress.text = takeProggres.toString()
            progresstimer = binding.Proggress.text.toString().toInt()
            startTimer()
        }

        binding.seekBar.setOnSeekBarChangeListener(this)


        binding.btnStart.setOnClickListener {
            binding.seekBar.isEnabled=false
            binding.btnStart.text="STOP"
            if (progressFlag) {
                progressmax = binding.Proggress.text.toString().toInt()
                progresstimer = binding.Proggress.text.toString().toInt()
                progressFlag=false
                startTimer()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TIME, binding.Proggress.text.toString().toInt())
        outState.putBoolean(KEY_THREAD, progressFlag)
        outState.putInt(KEY_MAX_PROGRESS, binding.progressBar.max)
    }

    fun startTimer(){
        first.launch {
            binding.progressBar.max = progressmax
            binding.progressBar.progress=progresstimer
            repeat(progresstimer) {
                binding.progressBar.progress--
                progresstimer--
                binding.Proggress.text = progresstimer.toString()
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
    }

    override fun onDestroy() {
        first.coroutineContext.cancelChildren()
        super.onDestroy()
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
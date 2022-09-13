package com.example.skillbox_1

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.skillbox_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var countSeat=0
        val seat=49
        binding.btnMinus.isEnabled=false

        binding.btnMinus.setOnClickListener {
            countSeat--
            if(countSeat<=0){
                binding.SeatCount.setTextColor(resources.getColor(R.color.min, null))
                binding.btnMinus.isEnabled=false
                binding.SeatCount.text=resources.getText(R.string.Seat)
            }else {
                if (countSeat > seat) {
                    binding.SeatCount.setTextColor(resources.getColor(R.color.max, null))
                    binding.SeatCount.text = "Пассажиров слишком много"
                } else {
                    binding.SeatCount.setTextColor(getResources().getColor(R.color.normal, null))
                    binding.SeatCount.text = "Осталось мест ${seat-countSeat}"
                }

            }
            binding.textView.text = countSeat.toString()
        }

        binding.btnPlus.setOnClickListener {
            if (countSeat<=0){
                binding.btnMinus.isEnabled=true
            }
            countSeat++
            if(countSeat> seat){
                binding.SeatCount.setTextColor(resources.getColor(R.color.max, null))
                binding.SeatCount.text = "Пассажиров слишком много"
                binding.btnReset.visibility= View.VISIBLE
            }else {
                binding.SeatCount.setTextColor(resources.getColor(R.color.normal, null))
                binding.SeatCount.text = "Осталось мест ${seat-countSeat}"
            }
            binding.textView.text=countSeat.toString()
        }

        binding.btnReset.setOnClickListener {
            countSeat=0
            binding.btnMinus.isEnabled=false
            binding.SeatCount.setTextColor(resources.getColor(R.color.min, null))
            binding.SeatCount.text=resources.getText(R.string.Seat)
            binding.textView.text=countSeat.toString()
        }

    }
}
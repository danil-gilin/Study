package com.example.skillbox_hw_quiz.ui.main

import android.icu.util.Calendar
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.MainFragmentBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialStyledDatePickerDialog
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding : MainFragmentBinding?=null
    private val binding get()= _binding!!
    private lateinit var viewModel: MainViewModel

    private val dateformat=SimpleDateFormat("dd-MM-yy hh:mm")
    private val calendar=Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater)

        binding.dateBtn.setOnClickListener {
            val calendarConstrain=CalendarConstraints.Builder()
                .setOpenAt(calendar.timeInMillis)
                .build()
            val datePicker= MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.title_date_main_fragment))
                .setCalendarConstraints(calendarConstrain)
                .build()
            datePicker.addOnPositiveButtonClickListener {
                calendar.timeInMillis=it
                Snackbar.make(binding.main,dateformat.format(calendar.time),Snackbar.LENGTH_LONG).show()
            }
            datePicker.show(childFragmentManager,"dateDialog")
        }

        binding.btnGo.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_quizFragment)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
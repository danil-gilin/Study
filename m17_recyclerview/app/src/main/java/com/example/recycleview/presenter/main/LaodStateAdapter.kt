package com.example.recycleview.presenter.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.databinding.LoadStateBinding

class LaodStateAdapter:LoadStateAdapter<LaodStateViewHolder>() {
    override fun onBindViewHolder(holder: LaodStateViewHolder, loadState: LoadState)=Unit

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LaodStateViewHolder {
        val binding=LoadStateBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return LaodStateViewHolder(binding)
    }
}

class LaodStateViewHolder(binding: LoadStateBinding):RecyclerView.ViewHolder(binding.root)
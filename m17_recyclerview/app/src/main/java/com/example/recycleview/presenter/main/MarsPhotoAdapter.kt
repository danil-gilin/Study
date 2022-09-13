package com.example.recycleview.presenter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recycleview.databinding.MarsItemBinding
import com.example.recycleview.entity.MarsPhoto.MarsPhoto
import com.example.recycleview.entity.MarsPhoto.Photo

class MarsPhotoAdapter(private val onClick :(Photo, FragmentNavigator.Extras)->Unit): PagingDataAdapter<Photo,MarsViewHolder>(DiffUtilCalback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
       return MarsViewHolder(MarsItemBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false))
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        val item=getItem(position)
        if(item!=null){
        with(holder.binding){
            rover.text=item.rover.name
            date.text=item.earthDate
            sol.text=item.sol.toString()
            camera.text=item.camera.name
            item.let {
                Glide.with(imgMars.context)
                    .load(it.imgSrc)
                    .into(imgMars)
            }
        }
        }
        holder.binding.root.setOnClickListener {
            item?.let {
                holder.binding.imgMars.transitionName="Img_Full_Transition"
                val extras = FragmentNavigatorExtras(
                    holder.binding.imgMars to "Img_Full_Transition",
                )
                onClick(item,extras)
            }

        }
    }
}


class MarsViewHolder(val binding: MarsItemBinding): RecyclerView.ViewHolder(binding.root)

class DiffUtilCalback: DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.equals(newItem)
    }
}
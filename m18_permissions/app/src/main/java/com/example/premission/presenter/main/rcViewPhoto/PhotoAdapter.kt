package com.example.premission.presenter.main.rcViewPhoto

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.premission.R
import com.example.premission.databinding.PhotoBinding
import com.example.premission.entity.photo.Photo

class PhotoAdapter:ListAdapter<Photo,PhotoViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
     return PhotoViewHolder(PhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item=getItem(position)
        with(holder.binding){
            Log.d("Photo",item.url_img)
            holder.binding.progressBar2.visibility=View.VISIBLE
            Glide.with(imgPhoto.context)
                .load(item.url_img)
                .addListener(object :RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.binding.progressBar2.visibility=View.GONE
                       return false
                    }
                })
                .centerInside()
                .into(imgPhoto)


            date.text=item.date
        }
    }
}

class PhotoViewHolder(val binding: PhotoBinding):RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback: DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
       return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem==newItem
    }
}
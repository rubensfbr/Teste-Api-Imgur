package com.rfb.testeapiimgur.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rfb.testeapiimgur.databinding.ItemRecyclerBinding
import com.rfb.testeapiimgur.model.Image
import com.rfb.testeapiimgur.model.Imagens
import com.squareup.picasso.Picasso

class ApiAdapter : ListAdapter<Image, ApiViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        return ApiViewHolder(
            ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        val diffCallback = object : ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem ==newItem
            }
        }
    }


}


class ApiViewHolder(private val binding: ItemRecyclerBinding) : ViewHolder(binding.root) {

    fun bind(item: Image) {

        Picasso.get()
            .load(item.link)
            .into(binding.imagePhotos)

    }


}


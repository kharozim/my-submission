package com.ozimos.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozimos.myapplication.data.remote.DataResponse
import com.ozimos.myapplication.databinding.ItemLayoutBinding

class DataAdapter : ListAdapter<DataResponse, DataAdapter.MyViewHolder>(MyDiffUtil()) {
    inner class MyViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: DataResponse) {
            binding.tvTitle.text = item.title
            binding.tvBody.text = item.body
        }

    }

    class MyDiffUtil : DiffUtil.ItemCallback<DataResponse>() {
        override fun areItemsTheSame(oldItem: DataResponse, newItem: DataResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataResponse, newItem: DataResponse): Boolean {
            return oldItem.id ?: 0 == newItem?.id ?: 0
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}
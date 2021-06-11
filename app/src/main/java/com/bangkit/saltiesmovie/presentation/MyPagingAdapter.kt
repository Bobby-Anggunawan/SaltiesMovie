package com.bangkit.saltiesmovie.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.saltiesmovie.core.domainlayer.model.MoviePageDomainMod
import com.bangkit.saltiesmovie.databinding.PageItemBinding
import com.bumptech.glide.Glide

object MyPagingAdapter{
    object ItemComparator : DiffUtil.ItemCallback<MoviePageDomainMod>() {
        override fun areItemsTheSame(
            oldItem: MoviePageDomainMod,
            newItem: MoviePageDomainMod
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MoviePageDomainMod,
            newItem: MoviePageDomainMod
        ): Boolean {
            return oldItem == newItem
        }
    }

    class MyPagingAdapter(private val click: myItemClickListener) :
        PagingDataAdapter<MoviePageDomainMod, MyPagingAdapter.MyViewHolder>(
            ItemComparator
        ) {

        data class myItemClickListener(val clickListener: (data: MoviePageDomainMod) -> Unit)

        class MyViewHolder(private var binding: PageItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(
                item: MoviePageDomainMod,
                clickListener: (MoviePageDomainMod) -> Unit
            ) {
                binding.judul.text = item.original_title
                Glide.with(binding.poster.context).load("https://www.themoviedb.org/t/p/w220_and_h330_face"+item.poster_path).into(binding.poster)

                itemView.setOnClickListener { clickListener(item) }
            }
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val currentItem = getItem(position)
            if (currentItem != null) {
                holder.bind(currentItem, click.clickListener)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val binding =
                PageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MyViewHolder(binding)
        }
    }
}
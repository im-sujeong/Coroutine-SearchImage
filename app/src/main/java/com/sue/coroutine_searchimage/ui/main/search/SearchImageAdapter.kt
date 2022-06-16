package com.sue.coroutine_searchimage.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sue.coroutine_searchimage.databinding.ItemImageBinding
import com.sue.coroutine_searchimage.domain.model.ImageModel
import com.sue.coroutine_searchimage.extensions.loadImage

class SearchImageAdapter(
    private val onClickImage: (ImageModel) -> Unit
): PagingDataAdapter<ImageModel, SearchImageAdapter.ViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ImageModel>() {
            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem.thumbnail == newItem.thumbnail
            }

            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(
        private val binding: ItemImageBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ImageModel?) = with(binding){
            model?.let { image ->
                iv.loadImage(
                    image.thumbnail,
                    4
                )

                root.setOnClickListener {
                    onClickImage(image)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
package com.javadsh98.hilttest.presentation.fragment.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.javadsh98.hilttest.presentation.model.UserItemModel
import com.javadsh98.usersearcher.databinding.RowItemUserBinding

typealias UserItemClick = (UserItemModel) -> Unit

class UsersAdapter(
    private val onItemClick: UserItemClick
) : ListAdapter<UserItemModel, UsersAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<UserItemModel>() {
                override fun areItemsTheSame(
                    oldItem: UserItemModel,
                    newItem: UserItemModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: UserItemModel,
                    newItem: UserItemModel
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            RowItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    inner class ViewHolder(
        private val binding: RowItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserItemModel, onItemClick: UserItemClick) = with(binding) {
            root.setOnClickListener { onItemClick(item) }
            ivUserPic.load(item.avatarUrl) {
                crossfade(true)
            }
            tvUserName.text = item.username
        }

    }
}
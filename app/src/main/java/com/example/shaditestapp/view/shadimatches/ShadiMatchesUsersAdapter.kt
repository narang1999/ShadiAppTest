package com.example.shaditestapp.view.shadimatches

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shaditestapp.R
import com.example.shaditestapp.databinding.ShadiUserItemBinding
import com.example.shaditestapp.extention.inflateChild
import com.example.shaditestapp.extention.loadFromURL
import com.example.shaditestapp.model.ShadiUserMatchesInfo

class ShadiMatchesUsersAdapter(val result: ((Pair<String, Int>) -> Unit)) :
    ListAdapter<ShadiUserMatchesInfo, RecyclerView.ViewHolder>(diffCallback) {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ShadiUserMatchesInfo>() {
            override fun areItemsTheSame(oldItem: ShadiUserMatchesInfo, newItem: ShadiUserMatchesInfo): Boolean =
                oldItem.email == newItem.email

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ShadiUserMatchesInfo, newItem: ShadiUserMatchesInfo): Boolean =
                oldItem.email == newItem.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(ShadiUserItemBinding.bind(parent.inflateChild(R.layout.shadi_user_item)))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PostViewHolder) {
            holder.bind(getItem(position))
        }
    }

    private inner class PostViewHolder(val binding: ShadiUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ShadiUserMatchesInfo) {
            binding.categoryGroupIcon.loadFromURL(item.picture)
            binding.userName.text = item.name
            binding.email.text = item.email
            binding.age.text = item.age.toString()
            setIsRecyclable(false)
            when (item.accept) {
                "accept" -> {
                    binding.acceptedLabel.visibility = View.VISIBLE
                    binding.accept.visibility = View.GONE
                    binding.decline.visibility = View.VISIBLE
                    binding.declinedLabel.visibility = View.GONE
                }
                "decline" -> {
                    binding.accept.visibility = View.VISIBLE
                    binding.declinedLabel.visibility = View.VISIBLE
                    binding.decline.visibility = View.GONE
                    binding.acceptedLabel.visibility = View.GONE
                }
                "default" -> {
                    binding.acceptedLabel.visibility = View.GONE
                    binding.declinedLabel.visibility = View.GONE
                    binding.accept.visibility = View.VISIBLE
                    binding.decline.visibility = View.VISIBLE
                }
            }
            binding.accept.setOnClickListener {
                binding.acceptedLabel.visibility = View.VISIBLE
                binding.accept.visibility = View.GONE
                binding.decline.visibility = View.VISIBLE
                binding.declinedLabel.visibility = View.GONE
                result("accept" to item.id)
            }
            binding.decline.setOnClickListener {
                binding.accept.visibility = View.VISIBLE
                binding.declinedLabel.visibility = View.VISIBLE
                binding.decline.visibility = View.GONE
                binding.acceptedLabel.visibility = View.GONE
                result("decline" to item.id)
            }

        }
    }
}
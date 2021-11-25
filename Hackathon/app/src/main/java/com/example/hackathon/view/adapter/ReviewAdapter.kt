package com.example.hackathon.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.databinding.OwnerReviewItemBinding
import com.example.hackathon.domain.entity.Review
import com.example.hackathon.domain.entity.Store

class ReviewAdapter(val itemClickListener: RecyclerViewItemClickListener<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private var reviewList = mutableListOf<Review>()

    class ReviewViewHolder(val binding: OwnerReviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Review) {
            binding.data = data
            binding.executePendingBindings()

        }


        companion object {
            fun from(parent: ViewGroup): ReviewViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: OwnerReviewItemBinding = DataBindingUtil
                    .inflate(
                        layoutInflater, R.layout.owner_review_item,
                        parent, false
                    )
                return ReviewViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviewList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onclick(reviewList[position])
        }

    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun setData(data: List<Review>) {

        val reviewDiffUtil = ReviewDiffUtil(reviewList, (data))
        val diffUtilResult = reviewDiffUtil.let { DiffUtil.calculateDiff(it) }
        reviewList = data.toMutableList()
        diffUtilResult.dispatchUpdatesTo(this)
    }


    class ReviewDiffUtil(
        private val oldList: List<Review>,
        private val newList: List<Review>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] === newList[newItemPosition]


        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] === newList[newItemPosition]


        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]

            return getChangePayload(
                oldItemPosition,
                newItemPosition
            )
        }


    }
}
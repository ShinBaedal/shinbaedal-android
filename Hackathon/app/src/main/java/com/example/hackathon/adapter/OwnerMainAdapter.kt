package com.example.hackathon.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.databinding.OwnerMainFragmentBinding
import com.example.hackathon.databinding.OwnerMainItemBinding
import com.example.hackathon.domain.entity.Store
import com.example.hackathon.view.adapter.RecyclerViewItemClickListener

class OwnerMainAdapter(val onClickListener: RecyclerViewItemClickListener<Store>) :
    RecyclerView.Adapter<OwnerMainAdapter.OwnerMainViewHolder>() {


    private val ownerMainList = mutableListOf<Store>()

    inner class OwnerMainViewHolder(private val binding: OwnerMainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Store) {
            binding.data = data
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onClickListener.onclick(data)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OwnerMainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<OwnerMainItemBinding>(
            layoutInflater,
            R.layout.owner_main_item,
            parent,
            false
        )

        return OwnerMainViewHolder(binding)
    }

    fun setList(list: List<Store>) {
        Log.d("ownerAdapter",list.toString())
        ownerMainList.clear()
        ownerMainList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: OwnerMainViewHolder, position: Int) {
        holder.bind(ownerMainList[position])
    }

    override fun getItemCount(): Int {
        return ownerMainList.size
    }
}
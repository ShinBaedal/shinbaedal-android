package com.example.hackathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.databinding.OwnerMainFragmentBinding
import com.example.hackathon.databinding.OwnerMainItemBinding
import com.example.hackathon.domain.entity.Store

class OwnerMainAdapter : RecyclerView.Adapter<OwnerMainAdapter.OwnerMainViewHolder>() {


    private val ownerMainList = mutableListOf<Store>()

    class OwnerMainViewHolder(private val binding: OwnerMainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Store) {
            binding.data = data
            binding.executePendingBindings()
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
    fun setList(list:List<Store>){
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
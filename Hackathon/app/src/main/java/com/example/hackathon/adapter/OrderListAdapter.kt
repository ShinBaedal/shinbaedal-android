package com.example.hackathon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.databinding.OwneListItemBinding
import com.example.hackathon.databinding.OwnerMainItemBinding
import com.example.hackathon.domain.entity.Order
import com.example.hackathon.domain.entity.Store

class OrderListAdapter : RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>() {
    private val orderList = mutableListOf<Order>()

    class OrderListViewHolder(private val binding: OwneListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Order) {
//            binding.data = data
            binding.executePendingBindings()

            binding.root.setOnClickListener {
//                onClickListener.onclick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<OwneListItemBinding>(
            layoutInflater,
            R.layout.owne_list_item,
            parent,
            false
        )

        return OrderListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        holder.bind(orderList[position])
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}


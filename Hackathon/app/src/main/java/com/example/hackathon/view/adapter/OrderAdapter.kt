package com.example.hackathon.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.domain.entity.Order

class OrderAdapter(val itemClickListener: RecyclerViewItemClickListener<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private val orderList = arrayListOf<Order>()

    inner class OrderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName=view.findViewById<TextView>(R.id.tv_title_order_item)
        val tvMenu=view.findViewById<TextView>(R.id.tv_menu_order_item)
        val tvState=view.findViewById<TextView>(R.id.tv_state_order_item)
        fun bind(order: Order) {
            var menus=""
            tvName.text=order.storeName
            for (i in order.menuNames.indices){
                menus+=order.menuNames[i]
                if (i==order.menuNames.lastIndex)break
                menus+="\n"
            }
            tvMenu.text=menus
            tvState.text=if (order.isDone)"주문 완료" else "주문 대기"

            view.setOnClickListener { itemClickListener.onclick(order) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.order_rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orderList[position])
    }

    override fun getItemCount(): Int = orderList.size

    fun setList(list:List<Order>){
        orderList.clear()
        orderList.addAll(list)
        notifyDataSetChanged()
    }
}
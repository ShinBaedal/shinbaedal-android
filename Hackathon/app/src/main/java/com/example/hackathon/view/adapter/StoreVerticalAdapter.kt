package com.example.hackathon.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.hackathon.R
import com.example.hackathon.domain.entity.Store

class StoreVerticalAdapter(val itemClickListener: RecyclerViewItemClickListener<Store>) :
    RecyclerView.Adapter<StoreVerticalAdapter.StoreViewHolder>() {
    val storeList = arrayListOf<Store>()

    inner class StoreViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById<TextView>(R.id.tv_name_rv_item_menu_vertical)
        val tvCategory: TextView =
            view.findViewById<TextView>(R.id.tv_category_rv_item_menu_vertical)
        val tvPhone: TextView = view.findViewById<TextView>(R.id.tv_phone_rv_item_menu_vertical)
        val tvAddress: TextView =
            view.findViewById<TextView>(R.id.tv_location_rv_item_menu_horizontal)
        val img: ImageView = view.findViewById<ImageView>(R.id.img_store_rv_item_menu_vertical)
        fun bind(store: Store) {
            tvName.text = store.name
            tvCategory.text = store.category
            tvAddress.text = store.address
            tvPhone.text = store.tell

            img.load(store.photoUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            view.setOnClickListener { itemClickListener.onclick(store) }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_store_vertical, parent, false)
        )
    }


    override fun getItemCount(): Int = storeList.size
    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(storeList[position])
    }

    fun setItem(list: List<Store>) {
        storeList.clear()
        storeList.addAll(list)
        notifyDataSetChanged()
    }
}
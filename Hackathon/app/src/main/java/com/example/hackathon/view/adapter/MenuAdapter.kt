package com.example.hackathon.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hackathon.R
import com.example.hackathon.domain.entity.Menu

class MenuAdapter(val onClickListener: RecyclerViewItemClickListener<com.example.hackathon.domain.entity.Menu>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    private var menuList = arrayListOf<Menu>()

    inner class MenuViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name_rv_item_add_store)
        val tvPrice = view.findViewById<TextView>(R.id.tv_price_rv_item_add_store)
        val img = view.findViewById<ImageView>(R.id.img_photo_rv_item_owner_menu)

        fun bind(menu: Menu) {
            tvName.text=menu.name
            tvPrice.text="${menu.price}원"
            img.load(menu.photoUrl)

            view.setOnClickListener {
                onClickListener.onclick(menu)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.owner_menu_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuList[position])
    }

    override fun getItemCount(): Int = menuList.size

    fun setList(list: List<Menu>) {
        menuList.clear()
        menuList.addAll(list)
        notifyDataSetChanged()
    }
}
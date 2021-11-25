package com.example.hackathon.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hackathon.R
import com.example.hackathon.domain.entity.Menu
import com.example.hackathon.domain.request.MenuRequest

class AddStoreMenuAdapter(private var menuList:ArrayList<MenuRequest>,val onClickListener: RecyclerViewItemClickListener<MenuRequest>) :
    RecyclerView.Adapter<AddStoreMenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName=view.findViewById<TextView>(R.id.tv_name_rv_item_add_store)
        val tvPrice=view.findViewById<TextView>(R.id.tv_price_rv_item_add_store)
        val img=view.findViewById<ImageView>(R.id.img_photo_rv_item_add_store)
        val btnDelete=view.findViewById<ImageButton>(R.id.btn_delete_rv_item_add_store)

        fun bind(menu: MenuRequest) {
            tvName.text=menu.name
            tvPrice.text="${menu.price}원"
            img.load(menu.photoUrl)
            btnDelete.setOnClickListener {
                onClickListener.onclick(menu)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_add_store_menu, parent, false)
        )
    }
    override fun getItemCount(): Int = menuList.size

    fun setList(list: List<MenuRequest>) {
        menuList.clear()
        menuList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuList[position])
    }

}
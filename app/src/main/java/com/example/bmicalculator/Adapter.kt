package com.example.bmicalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bmicalculator.databinding.ItemRowBinding

class Adapter(val list:ArrayList<Info>): RecyclerView.Adapter<Adapter.MyView>() {

    class MyView(val itemBinding: ItemRowBinding): RecyclerView.ViewHolder(itemBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        return MyView(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.itemBinding.imageView.setImageResource(list[position].image)
        holder.itemBinding.textView.text = list[position].name
        holder.itemBinding.tvDesc.text = list[position].desc
        holder.itemBinding.tvTiming.text = list[position].timing
        holder.itemBinding.tvType.text = list[position].type
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
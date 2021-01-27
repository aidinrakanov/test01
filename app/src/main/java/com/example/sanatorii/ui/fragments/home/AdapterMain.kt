package com.example.sanatorii.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sanatorii.R
import com.example.sanatorii.model.Model
import kotlinx.android.synthetic.main.card_list.view.*

class AdapterMain(
    private val onItemClickListener: OnItemClickListener, val list: MutableList<Model>
) : RecyclerView.Adapter<AdapterMain.MainVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVH {
        return MainVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainVH, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onClickListener(list[position])
        }
    }

    override fun getItemCount(): Int = list.size


    class MainVH(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(model: Model) {
            itemView.card_text_name.text = model.name
            itemView.card_cost.text = model.cost.toString()
            itemView.card_small_info.text = model.info
            itemView.card_adress_text.text = model.adress
            itemView.card_rating.rating = model.rating
            Glide.with(itemView.context).load(model.images).into(itemView.card_image)
        }
    }

    interface OnItemClickListener {
        fun onClickListener(item: Model)
    }
}



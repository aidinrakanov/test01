package com.example.sanatorii.ui.fragments.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sanatorii.R
import com.example.sanatorii.model.PostsModel
import kotlinx.android.synthetic.main.posts_list.view.*

class PostAdapter( private val onItemClickListener: OnItemClickListener,
                   private val posts_list: MutableList<PostsModel>): RecyclerView.Adapter<PostAdapter.PostsVH>() {

    private var postLists = mutableListOf<PostsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsVH {
        return PostsVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.posts_list, parent, false))
    }

    override fun onBindViewHolder(holder: PostsVH, position: Int) {
        holder.bind(posts_list[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onClickListener(posts_list[position])
        }

    }

    override fun getItemCount(): Int  = posts_list.size


    class PostsVH(item:View): RecyclerView.ViewHolder(item) {
        fun bind (postsModel: PostsModel){
            itemView.post_text_title.text = postsModel.post_title
            itemView.post_text_descr.text = postsModel.post_descr
            Glide.with(itemView.context).load(postsModel.post_image).into(itemView.post_image)
        }

    }
    interface OnItemClickListener {
        fun onClickListener(item: PostsModel)
    }

}
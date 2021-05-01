package com.example.sanatorii.ui.fragments.posts

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanatorii.R
import com.example.sanatorii.model.PostsModel
import kotlinx.android.synthetic.main.posts_list.view.*

class PostAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PostAdapter.PostsVH>() {
    private val postList = mutableListOf<PostsModel>()

    fun addPost(arrayList: ArrayList<PostsModel>) {
        if (postList.size < 0) {
            postList.addAll(arrayList)
            notifyDataSetChanged()
        } else {
            var isChanged = false
            for (i in arrayList.indices) {
                val task = arrayList[i]
                for (b in postList.indices) {
                    if (task.title == postList[b].title) {
                        postList[b] = task
                        isChanged = true
                        notifyItemChanged(b)
                    }
                }
                if (!isChanged) {
                    postList.add(task)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsVH {
        return PostsVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.posts_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostsVH, position: Int) {
        holder.bind(postList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onClickListener(postList[position])
        }
    }

    override fun getItemCount(): Int = postList.size

    class PostsVH(item: View) : RecyclerView.ViewHolder(item) {
        fun bind(postsModel: PostsModel) {
            itemView.post_text_title.text = postsModel.title
            itemView.post_text_descr.text = postsModel.desc
            itemView.post_image.setImageURI(Uri.parse(postsModel.image))
        }
    }

    interface OnItemClickListener {
        fun onClickListener(item: PostsModel)
    }

}
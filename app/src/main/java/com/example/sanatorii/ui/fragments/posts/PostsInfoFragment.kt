package com.example.sanatorii.ui.fragments.posts

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.sanatorii.R
import com.example.sanatorii.model.PostsModel
import kotlinx.android.synthetic.main.posts_list.*



class PostsInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_postsinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPostData()
    }

    private fun setPostData() {
            Glide.with(post_image.context).load(item?.post_image).into(post_image)
            post_text_title.text = item?.post_title.toString()
            post_text_descr.text = item?.post_descr
            posts_date.text = item?.post_date
    }

    companion object {
        var item: PostsModel? = null
        fun start(activity: Activity, action: Int, item: PostsModel) {
            this.item = item
            Navigation.findNavController(activity, R.id.nav_host_fragment)
                .navigate(action)
        }
    }
}


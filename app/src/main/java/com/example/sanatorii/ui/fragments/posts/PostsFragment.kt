package com.example.sanatorii.ui.fragments.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanatorii.R
import com.example.sanatorii.model.PostsModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_posts.*

class PostsFragment: Fragment(), PostAdapter.OnItemClickListener {

    private lateinit var postsAdapter: PostAdapter
    private var listPosts = mutableListOf<PostsModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_postsinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_init()
        if (FirebaseAuth.getInstance().currentUser?.email.equals("altynaikanatbekova@gmail.com")){
//            fab.visibility = View.VISIBLE
        }

    }

    private fun recycler_init() {
        postsAdapter = PostAdapter(this, listPosts)
        posts_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter }

    }

    override fun onClickListener(item: PostsModel) {
            PostsInfoFragment.start(requireActivity(),
                R.id.action_navigation_posts_to_postsInfoFragment, item)
    }

}
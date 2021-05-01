package com.example.sanatorii.ui.fragments.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanatorii.R
import com.example.sanatorii.model.PostsModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_posts.*

class PostsFragment : Fragment(), PostAdapter.OnItemClickListener {

    private lateinit var postsAdapter: PostAdapter
    private val viewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkIsAdmin()
        viewModelObs()
        recycler_init()
    }

    private fun checkIsAdmin() {
        if (FirebaseAuth.getInstance().currentUser?.email.equals("altynaikanatbekova@gmail.com") || FirebaseAuth.getInstance().currentUser?.email.equals(
                "kelsinbekovadilhan2018@gmail.com"
            )
        ) {
            upload_btn.visibility = View.VISIBLE
            upload_btn.setOnClickListener {
                findNavController().navigate(R.id.uploadFragment)
            }
        }
    }

    private fun viewModelObs() {
        viewModel.getPostList()

        viewModel.postLiveData.observe(viewLifecycleOwner) {
            postsAdapter.addPost(it)
        }
    }

    private fun recycler_init() {
        postsAdapter = PostAdapter(this)
        posts_recycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = postsAdapter
        }

    }

    override fun onClickListener(item: PostsModel) {
        PostsInfoFragment.start(
            requireActivity(),
            R.id.action_navigation_posts_to_postsInfoFragment, item
        )
    }

}
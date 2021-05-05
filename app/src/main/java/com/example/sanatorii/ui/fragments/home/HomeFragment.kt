package com.example.sanatorii.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanatorii.R
import com.example.sanatorii.model.Model
import com.example.sanatorii.ui.fragments.InfoFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), AdapterMain.OnItemClickListener {

    private lateinit var homeAdapter: AdapterMain
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (FirebaseAuth.getInstance().currentUser == null) {
            findNavController().navigate(R.id.loginFragment)
        }
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerSets()
        observeData()
    }

    private fun observeData() {
        viewModel.getData()

        viewModel.state.observeForever {
            if (it != null) {
                homeAdapter.setDataList(it)
                main_item_count.text = ("общее количество  " + it.size)
            }
        }
    }

    private fun recyclerSets() {
        homeAdapter = AdapterMain(this)
        main_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
    }

    override fun onClickListener(item: Model, position: Int) {
        InfoFragment.start(
            requireActivity(),
            R.id.action_navigation_home_to_infoFragment, item, position
        )
    }

}

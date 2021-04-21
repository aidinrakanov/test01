package com.example.sanatorii.ui.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanatorii.R
import com.example.sanatorii.model.Model
import com.example.sanatorii.repository.State
import com.example.sanatorii.repository.Status
import com.example.sanatorii.ui.fragments.InfoFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), AdapterMain.OnItemClickListener {

    private lateinit var homeAdapter: AdapterMain
    private val viewModel: HomeViewModel by viewModel()
    private var listHome: MutableList<Model> = mutableListOf()
    private val db = FirebaseFirestore.getInstance().collection("kurort")

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
        main_item_count.text = ("общее количество  " + listHome.size)

    }

    private fun observeData() {
        CoroutineScope(Dispatchers.IO).launch {
            db.get().addOnSuccessListener {
                for (document in it) {
                    Log.d("ololo", "${document.id} => ${document.data}")
                    val model = document.toObject(Model::class.java)
                    listHome.add(model)
                    homeAdapter.setDataList(listHome)
                }
            }
        }
    }

    private fun recyclerSets() {
        homeAdapter = AdapterMain(this, listHome)
        main_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
    }


    override fun onClickListener(item: Model) {
        InfoFragment.start(
            requireActivity(),
            R.id.action_navigation_home_to_infoFragment, item
        )
    }

}

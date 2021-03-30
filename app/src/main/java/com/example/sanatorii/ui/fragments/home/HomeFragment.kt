package com.example.sanatorii.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanatorii.R
import com.example.sanatorii.model.Model
import com.example.sanatorii.ui.fragments.InfoFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.GeoPoint
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment(), AdapterMain.OnItemClickListener {

    private lateinit var homeAdapter: AdapterMain
    private var listHome: MutableList<Model> = mutableListOf()


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
        setupSort()
//        test()
        main_item_count.text = ("общее количество  " + listHome.size)

    }


    private fun recyclerSets() {
        homeAdapter = AdapterMain(this, listHome)
        main_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter

        }
    }

    private fun test() {
            listHome.add(
                Model(
                    "https://bestway.kg/wp-content/uploads/2020/08/2-123.jpg",
                    "Чолпон - ата",
                    2000,
                    "круглый год, лечебный",
                    GeoPoint(42.64819715,77.10169331),
                    "Лечебница",
                    "Голубой Иссык-куль",
                    7.0f,
                    "070670900"
                )
            )

        }

        private fun setupSort() {
            val adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.sort,
                android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            main_spinner.adapter = adapter

            main_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    when (view?.id) {
                        0 -> sortByRating()
                        1 -> sortByName()
                        2 -> sortByPrice()
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                private fun sortByName() {
                    listHome.sortWith { t, t2 -> t.name.compareTo(t2.name) }
                    homeAdapter.notifyDataSetChanged()
                }

                private fun sortByRating() {
                    listHome.sortBy { it.cost }
                    homeAdapter.notifyDataSetChanged()
                }

                private fun sortByPrice() {
                    listHome.sortBy { it.rating }
                    homeAdapter.notifyDataSetChanged()
                }
            }
        }

    override fun onClickListener(item: Model) {
        InfoFragment.start(
            requireActivity(),
            R.id.action_navigation_home_to_infoFragment, item
        )
    }

}

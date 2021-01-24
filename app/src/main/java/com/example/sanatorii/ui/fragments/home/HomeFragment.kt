package com.example.sanatorii.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanatorii.R
import com.example.sanatorii.model.Model
import com.example.sanatorii.ui.fragments.InfoFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), AdapterMain.OnItemClickListener {

    private lateinit var homeAdapter: AdapterMain
    private var listHome: MutableList<Model> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main_item_count.text = ("общее количество  " + listHome.size.toString())
        recyclerSets()
        setupSort()
        test()
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
                "https://media.issyk-kul.pro/CACHE/images/hotel/3/3/7b9dd0da-83f8-4cd5-91e5-8f8a6f1f2531/6de5fdc268a37fe5ecaba0b7d6ee6d16.jpg",
                "Голубой Иссык куль",
                "Чолпон - ата",
                "круглый год, лечебный",
                2000, (7.0f), ""
            )
        )
        listHome.add(
            Model(
                "https://total.kz/storage/96/9656e07cf91c06b07f1696cc77d91b95.jpg",
                "Золотые пески",
                "Бостери",
                "молодежный",
                3000, 6.0f, ""
            )
        )
        listHome.add(
            Model(
                "https://media.issyk-kul.pro/CACHE/images/hotel/4/42/d9324d5e-6786-48f5-be00-e8b1722fe637/e391e238f369848649365f4836eebab4.jpg",
                "Аврора",
                "Аврора",
                "лечебный",
                2500, 5.0f, ""
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
                listHome.sortedBy { it.name }
                adapter.notifyDataSetChanged()
            }

            private fun sortByRating() {
                listHome.sortedBy { it.cost }
                adapter.notifyDataSetChanged()
            }

            private fun sortByPrice() {
                listHome.sortedBy { it.rating }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClickListener(item: Model) {
        InfoFragment.start(requireActivity(),
            R.id.action_navigation_home_to_infoFragment, item)
    }

}

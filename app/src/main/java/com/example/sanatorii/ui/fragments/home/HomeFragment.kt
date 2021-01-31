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
import java.util.*


class HomeFragment : Fragment(), AdapterMain.OnItemClickListener {

    private lateinit var homeAdapter: AdapterMain
    private var listHome: MutableList<Model> = mutableListOf()
//    private val list: List<Model> = ArrayList<Model>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerSets()
        setupSort()
        test()
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
                    "https://www.open.kg/uploads/posts/2015-07/1438265502_vip_1658.jpg",
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
                    Collections.sort(listHome, kotlin.Comparator { t, t2 -> t.name.compareTo(t2.name) })
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

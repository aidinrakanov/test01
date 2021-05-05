package com.example.sanatorii.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sanatorii.R
import com.example.sanatorii.model.Model
import com.example.sanatorii.utils.isNotEmpty
import com.example.sanatorii.utils.showToast
import kotlinx.android.synthetic.main.fragment_info.*
import java.util.*


class InfoFragment : Fragment() {
    private val viewModel: InfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeVM()
        setData()
        toCall()
        toMap()
        clickListeners()
    }

    private fun observeVM() {
        viewModel.isSuccessfully.observeForever {
            if (it) {
                findNavController().navigateUp()
                requireContext().showToast("Успешно отправлено!")
            } else {
                requireContext().showToast("Запрос провален!")
            }
        }
    }

    private fun clickListeners() {
        btn_send_feed.setOnClickListener {
            if (leave_feedback.isNotEmpty()) {
                position?.let { it1 ->
                    viewModel.postRating(
                        it1,
                        leave_feedback.text.toString(),
                        rating.rating.toDouble()
                    )
                }
            }
        }
    }

    private fun toMap() {
        info_map.setOnClickListener {
            val position_lat = item?.geoPosition?.latitude
            val position_long = item?.geoPosition?.longitude
            val uri = String.format(Locale.ENGLISH, "geo:%f,%f", position_lat, position_long)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }
    }

    private fun toCall() {
        info_call.setOnClickListener {
            val numbers = item?.telephone
            val intent2 = Intent(Intent.ACTION_DIAL)
            intent2.data = Uri.parse("tel:$numbers")
            startActivity(intent2)
        }
    }

    private fun setData() {
        Glide.with(info_image.context).load(item?.image).into(info_image)
        info_cost.text = item?.cost.toString()
        info_adress.text = item?.adress
        info_name.text = item?.name
        info_full_info.text = item?.fullInfo
    }

    companion object {
        var item: Model? = null
        var position: Int? = null
        fun start(activity: Activity, action: Int, item: Model, position: Int) {
            this.item = item
            this.position = position
            Navigation.findNavController(activity, R.id.nav_host_fragment)
                .navigate(action)
        }
    }


}


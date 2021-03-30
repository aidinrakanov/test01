package com.example.sanatorii.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.sanatorii.R
import com.example.sanatorii.model.Model
import kotlinx.android.synthetic.main.fragment_info.*
import java.util.*


class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_info, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        toCall()
        toMap()
    }

    private fun toMap() {
        info_map.setOnClickListener {
            var position = item?.geo
            val uri = String.format(Locale.ENGLISH, "geo:%f,%f", position)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }
    }

    private fun toCall() {
        info_call.setOnClickListener {
            val numbers = item?.tel
            val intent2 = Intent(Intent.ACTION_DIAL)
            intent2.data = Uri.parse("tel:$numbers")
            startActivity(intent2)
        }
    }

    private fun setData() {
        Glide.with(info_image.context).load(item?.images).into(info_image)
        info_cost.text = item?.cost.toString()
        info_adress.text = item?.adress
        info_name.text = item?.name
        info_full_info.text = item?.fullInfo
    }

    companion object {
        var item: Model? = null
        fun start(activity: Activity, action: Int, item: Model) {
            this.item = item
            Navigation.findNavController(activity, R.id.nav_host_fragment)
                .navigate(action)
        }
    }


}


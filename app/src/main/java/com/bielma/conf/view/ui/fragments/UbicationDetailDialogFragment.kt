package com.bielma.conf.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bielma.conf.R
import com.bielma.conf.model.Location
import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*


class UbicationDetailDialogFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.fullscreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication_detail_dialog, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarLocation.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_close_24)
        toolbarLocation.setTitleTextColor(Color.WHITE)

        toolbarLocation.setNavigationOnClickListener {
            dismiss()
        }
        val location = Location()

        lblDetailPLace.text = location.name
        lblDetailAddress.text = location.address
        lblDetailPhone.text = location.phone
        lblDetailWeb.text = location.webSite
        toolbarLocation.title =location.name
            abrirTelefono.setOnClickListener {
            var i = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:"+location.phone)
            }
            startActivity(i)
        }

        abrirWeb.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data= Uri.parse(location.webSite)
            startActivity(i)
        }


    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}
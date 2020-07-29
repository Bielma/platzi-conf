package com.bielma.conf.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bielma.conf.R
import com.bielma.conf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat


class ScheduleDetailDialogFragment: DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.fullscreenDialogStyle)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       toolbarConference.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_baseline_close_24);
        toolbarConference.setTitleTextColor(Color.WHITE)
        toolbarConference.setNavigationOnClickListener {
            dismiss()
        }

        val conference = arguments?.getSerializable("conference") as Conference
        toolbarConference.title = conference.title
        lblDetailConference.text = conference.title
        lblDetailConferenceDescription.text = conference.description
        lblDetailSpeaker.text = conference.speaker
        lblDetailTag.text = conference.tag
        val pattern = "dd/MM/yyyy hh:mm a"
        val simpleDF= SimpleDateFormat(pattern)
        val date = simpleDF.format(conference.dataTime)
        lblDetailConferenceHour.text = date


    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}
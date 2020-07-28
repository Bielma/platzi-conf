package com.bielma.conf.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bielma.conf.R
import com.bielma.conf.model.Speaker
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*


class SpeakersDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.fullscreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val speaker = arguments?.getSerializable("speaker") as Speaker
        toolbarSpeakerDialog.title = speaker.name
        toolbarSpeakerDialog.setTitleTextColor(Color.WHITE)
        lblDetailNombreSpeaker.text = speaker.name
        lblDetailTrabajoSpeaker.text = speaker.workPlace
        lblDetailTituloSpeaker.text = speaker.jobTitle
        lblDetailDescSpeaker.text = speaker.biograpy
        Glide.with(this)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(imgSpeakerFoto)
        imgTwitter.setOnClickListener {
            var i: Intent
            try {
                context?.packageManager?.getPackageInfo("com.twitter.android", 0)
                i = Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name="+speaker.tiwtter))
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }catch (e:Exception){
                i = Intent(
                    Intent.ACTION_VIEW, Uri.parse("https://twitter.con"+speaker.tiwtter)
                )
            }
            startActivity(i)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}
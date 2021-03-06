package com.bielma.conf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bielma.conf.R
import com.bielma.conf.model.Speaker
import com.bielma.conf.view.adapter.SpeakerAdapter
import com.bielma.conf.view.adapter.SpeakerListener
import com.bielma.conf.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*


class SpeakersFragment : Fragment(), SpeakerListener {

    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakerViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.getSpeakersFromFireStore()

        speakerAdapter = SpeakerAdapter(this)
        listSpeakers.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = speakerAdapter
        }
        observeViewModel()

    }

    fun observeViewModel(){
        viewModel.listSpeakers.observe(viewLifecycleOwner, Observer<List<Speaker>>{ speakers ->
            speakerAdapter.updateData(speakers)
        })
        viewModel.isLoading.observe(this.viewLifecycleOwner, Observer<Boolean>{
            if(it != null)
                rlBaseSpeakers.visibility = View.INVISIBLE
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)
        //findNavController().navigate(R.id.speakersDetailFragmentDialog, bundle)
        findNavController().navigate(R.id.speakersDetailFragmentDialog, bundle)
    }


}
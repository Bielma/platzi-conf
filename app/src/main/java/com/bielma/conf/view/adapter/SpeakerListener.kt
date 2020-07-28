package com.bielma.conf.view.adapter

import com.bielma.conf.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}
package com.bielma.conf.view.adapter

import com.bielma.conf.model.Conference

interface ScheduleListener {
    fun onConferenceClicked(conference: Conference, position: Int)
}
package com.bielma.conf.model

import java.io.Serializable
import java.util.*

class Conference: Serializable {
    lateinit var title: String
    lateinit var description: String
    lateinit var tag: String
    lateinit var dataTime: Date
    lateinit var speaker: String


}
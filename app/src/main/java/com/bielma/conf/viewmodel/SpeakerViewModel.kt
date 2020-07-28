package com.bielma.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bielma.conf.model.Speaker
import com.bielma.conf.network.Callback
import com.bielma.conf.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel: ViewModel() {
    val firestore = FirestoreService()
    var listSpeakers : MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading =  MutableLiveData<Boolean>()

    fun getSpeakersFromFireStore(){
        firestore.getSpeakers(object :Callback<List<Speaker>>{
            override fun onSucces(result: List<Speaker>) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }
    fun processFinished(){
        isLoading.value = false
    }



}
package com.bielma.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bielma.conf.model.Conference
import com.bielma.conf.network.Callback
import com.bielma.conf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel: ViewModel() {
    val fireStoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }
    fun getScheduleFromFirebase(){
        fireStoreService.getSchedule(object: Callback<List<Conference>>{
            override fun onSucces(result: List<Conference>) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })

    }

    fun processFinished(){
        isLoading.value = true
    }




}
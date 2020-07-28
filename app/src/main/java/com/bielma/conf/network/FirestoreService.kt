package com.bielma.conf.network
import com.bielma.conf.model.Conference
import com.bielma.conf.model.Speaker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings


class FirestoreService {
    val firebase = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()


    init{
        //Permite que las dotos sean persistentes y no requieran conexion a intenet
        firebase.firestoreSettings = settings
    }

    fun getSpeakers(callback: Callback<List<Speaker>>){
        firebase.collection("speakers")
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Speaker::class.java)
                    //val list = doc.toObject(Speaker::class.java)
                    callback.onSucces(list)
                    break
                }
            }
    }
    fun getSchedule(callback: Callback<List<Conference>>){
        firebase.collection("conferences")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Conference::class.java)
                    callback.onSucces(list)
                    break
                }
            }

    }


}
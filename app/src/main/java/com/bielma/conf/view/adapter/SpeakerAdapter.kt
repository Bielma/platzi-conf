package com.bielma.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bielma.conf.R
import com.bielma.conf.model.Speaker
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SpeakerAdapter(val speakerListener: SpeakerListener): RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {
    var speakersList = ArrayList<Speaker>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerAdapter.ViewHolder {
         return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker, parent,false))
    }

    override fun getItemCount(): Int {
        return speakersList.size
    }

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {
        var speaker = speakersList.get(position)
        holder.name.text = speaker.name
        holder.work.text = speaker.workPlace

        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.fotoSpeaker)

        holder.itemView.setOnClickListener{
            speakerListener.onSpeakerClicked(speaker, position)
        }

    }

    fun updateData(data: List<Speaker>){
        speakersList.clear()
        speakersList.addAll(data)
        notifyDataSetChanged()

    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fotoSpeaker = itemView.findViewById<ImageView>(R.id.imgItemSpeakeFoto)
        val name = itemView.findViewById<TextView>(R.id.lblItemSpeakerName)
        val work = itemView.findViewById<TextView>(R.id.lblItemSpeakerWork)

    }

}
package com.bielma.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bielma.conf.R
import com.bielma.conf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener: ScheduleListener):RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    var listConference = ArrayList<Conference>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))

    override fun getItemCount() = listConference.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        holder.speaker.text = listConference.get(position).speaker
        holder.conferenceName.text = listConference.get(position).title
        holder.tag.text = listConference.get(position).tag
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDataFormatAAPM = SimpleDateFormat("a")
        val cal = Calendar.getInstance()
        cal.time = listConference.get(position).dataTime
        val hourFormat = simpleDateFormat.format(listConference.get(position).dataTime)
        val ampmFormat = simpleDateFormat.format(listConference.get(position).dataTime).toUpperCase()
        holder.time.text =  hourFormat
        holder.APPM.text = ampmFormat


        holder.itemView.setOnClickListener{
            scheduleListener.onConferenceClicked(listConference.get(position), position)
        }
    }
    fun updateData(data: List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val time = itemView.findViewById<TextView>(R.id.lblHour)
        val APPM = itemView.findViewById<TextView>(R.id.lblMeridiano)
        val speaker = itemView.findViewById<TextView>(R.id.lblScheduleSpeaker)
        val conferenceName = itemView.findViewById<TextView>(R.id.lblScheduleConfName)
        val tag = itemView.findViewById<TextView>(R.id.lblScheduleTag)

    }

}
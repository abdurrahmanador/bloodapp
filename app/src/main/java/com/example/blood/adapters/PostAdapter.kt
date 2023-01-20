package com.example.blood.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.blood.R
import com.example.blood.utilities.PostUser
import com.example.blood.utilities.User

class PostAdapter(private var postUser: ArrayList<PostUser>):
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: Any) {
        mListener = listener as onItemClickListener
    }

    class PostViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.patientNameTextView)
        val bloodGroup: TextView = itemView.findViewById(R.id.patientBGTextView)
        val contact: TextView = itemView.findViewById(R.id.patientCNTextView)
        val patientAddress: TextView = itemView.findViewById(R.id.PatientaddressTextView)
        val patientDistrict: TextView = itemView.findViewById(R.id.PatientDistrictTextView)
        val patientAge: TextView = itemView.findViewById(R.id.patientAgeTextView)
        val bloodNeed: TextView = itemView.findViewById(R.id.patientBNTextView)
        val patientType: TextView = itemView.findViewById(R.id.patientTypeTextView)
        val managed: TextView = itemView.findViewById(R.id.managedET)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }


    }


    fun searchDataList(postUser: ArrayList<PostUser>){
        this.postUser= postUser
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.group_donor, parent, false)
        return PostViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val pUser: PostUser = postUser[position]

        holder.name.text = pUser.name
        holder.patientAge.text = pUser.age
        holder.patientAddress.text = pUser.address
        holder.bloodNeed.text = pUser.bloodBag
        holder.bloodGroup.text = pUser.bloodGroup
        holder.contact.text = pUser.mobile
        holder.patientType.text = pUser.illness
        holder.patientDistrict.text = pUser.district
        holder.managed.text=pUser.managed

    }

    override fun getItemCount(): Int {
        return postUser.size

    }
}
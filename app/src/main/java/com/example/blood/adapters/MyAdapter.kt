package com.example.blood.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.blood.R
import com.example.blood.utilities.User

class MyAdapter(private var userList: ArrayList<User>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private lateinit var mListener: ClickListener

    interface ClickListener {
        fun onItemClick(position: Int)
    }

    fun setClickListener(listener: Any) {
        mListener = listener as ClickListener
    }
    class MyViewHolder(itemView: View,listener:ClickListener) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameTextView)
        val bloodGroup: TextView = itemView.findViewById(R.id.bGTextView)
        val contact: TextView = itemView.findViewById(R.id.cNTextView)
        val districtName: TextView = itemView.findViewById(R.id.addressTextView)
        val availability: TextView = itemView.findViewById(R.id.availability)
//        val email:TextView=itemView.findViewById(R.id.emailProfileEt)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(userList: ArrayList<User>){
        this.userList= userList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.donor_list, parent, false)
        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User = userList[position]
       // holder.email.text=user.email
        holder.name.text = user.name
        holder.contact.text = user.contactNumber.toString()
        holder.bloodGroup.text = user.bloodGroup
        holder.districtName.text = user.district
        holder.availability.text = user.availability
    }


    override fun getItemCount(): Int {
        return userList.size
    }

}
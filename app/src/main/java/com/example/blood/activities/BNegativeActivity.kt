package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blood.R
import com.example.blood.adapters.MyAdapter
import com.example.blood.utilities.User
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import java.util.*

class BNegativeActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<com.example.blood.utilities.User>
    private lateinit var db: FirebaseFirestore
    private lateinit var myAdapter: MyAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("B- Donor")

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_b_negative)

        db = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.userList)
        searchView = findViewById(R.id.searchView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        userList = arrayListOf()

        myAdapter = MyAdapter(userList)
        recyclerView.adapter = myAdapter
        BNegativeList()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<User>()
            for (i in userList) {
                if (i.district?.lowercase(Locale.ROOT)!!.contains(query)) {
                    filteredList.add(i)
                }
            }
            if (filteredList.isEmpty()) {

            } else {
                myAdapter.setFilteredList(filteredList)
            }
        }
    }

    private fun BNegativeList() {
        db.collection("users").whereEqualTo("bloodGroup", "B-")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for (db: DocumentChange in value?.documentChanges!!) {
                        if (db.type == DocumentChange.Type.ADDED) {
                            userList.add(db.document.toObject(com.example.blood.utilities.User::class.java))
                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }

            })
        myAdapter.setClickListener(object : MyAdapter.ClickListener{
            override fun onItemClick(position: Int) {
                val intent= Intent(this@BNegativeActivity,PostDetails::class.java)
                intent.putExtra("name",userList[position].name)
                intent.putExtra("mobile",userList[position].contactNumber)
                intent.putExtra("bloodGroup",userList[position].bloodGroup)
                intent.putExtra("city",userList[position].city)
                intent.putExtra("district",userList[position].district)
                intent.putExtra("availability",userList[position].availability)
                startActivity(intent)
            }
        })
    }
}

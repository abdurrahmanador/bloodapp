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
import com.example.blood.databinding.ActivityAPositiveBinding
import com.example.blood.utilities.User
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import java.util.*
import kotlin.collections.ArrayList


class ApositiveActivity:AppCompatActivity() {
    private lateinit var binding: ActivityAPositiveBinding
    private lateinit var userList: ArrayList<User>
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var db:FirebaseFirestore
    private lateinit var myAdapter: MyAdapter
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("A+ Donor")

        super.onCreate(savedInstanceState)
        binding = ActivityAPositiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userRecyclerView = findViewById(R.id.userList)
        searchView=findViewById(R.id.searchView)
        userRecyclerView.layoutManager=LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userList = arrayListOf<User>()
        myAdapter = MyAdapter(userList)
        userRecyclerView.adapter = myAdapter
        getDataOfDonorA()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
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
        if(query!=null){
            val filteredList=ArrayList<User>()
            for(i in userList){
                if(i.district?.lowercase(Locale.ROOT)!!.contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){

            }else{
                myAdapter.setFilteredList(filteredList)
            }
        }
    }

    private fun getDataOfDonorA() {
        db= FirebaseFirestore.getInstance()
        db.collection("users").whereEqualTo("bloodGroup","A+")
            .addSnapshotListener(object :EventListener<QuerySnapshot>{
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?) {
                    if(error!=null){
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for (dc:DocumentChange in value?.documentChanges!!){
                        if(dc.type==DocumentChange.Type.ADDED){
                            userList.add(dc.document.toObject(User::class.java))
                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }

            })
        myAdapter.setClickListener(object : MyAdapter.ClickListener{
            override fun onItemClick(position: Int) {
                val intent= Intent(this@ApositiveActivity,DonorDetailsActivity::class.java)
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

/*    <string-array name="district">
        <item>Dhaka</item>
        <item>Faridpur</item>
        <item>Gazipur</item>
        <item>Gopalganj</item>
        <item>Jamalpur</item>
        <item>Kishoreganj</item>
        <item>Madaripur</item>
        <item>Manikganj</item>
        <item>Munshiganj</item>
        <item>Mymensingh</item>
        <item>Narayanganj</item>
        <item>Narsingdi</item>
        <item>Netrokona</item>
        <item>Rajbari</item>
        <item>Shariatpur</item>
        <item>Sherpur</item>
        <item>Tangail</item>
        <item>Bogra</item>
        <item>Joypurhat</item>
        <item>Naogaon</item>
        <item>Natore</item>
        <item>Nawabganj</item>
        <item>Pabna</item>
        <item>Rajshahi</item>
        <item>Sirajgonj</item>
        <item>Dinajpur</item>
        <item>Gaibandha</item>
        <item>Kurigram</item>
        <item>Lalmonirhat</item>
        <item>Nilphamari</item>
        <item>Panchagarh</item>
        <item>Rangpur</item>
        <item>Thakurgaon</item>
        <item>Barguna</item>
        <item>Barisal</item>
        <item>Bhola</item>
        <item>Jhalokati</item>
        <item>Patuakhali</item>
        <item>Pirojpur</item>
        <item>Bandarban</item>
        <item>Brahmanbaria</item>
        <item>Chandpur</item>
        <item>Chittagong</item>
        <item>Comilla</item>
        <item>Cox''s Bazar</item>
        <item>Feni</item>
        <item>Khagrachari</item>
        <item>Lakshmipur</item>
        <item>Noakhali</item>
        <item>Rangamati</item>
        <item>Habiganj</item>
        <item>Maulvibazar</item>
        <item>Sunamganj</item>
        <item>Sylhet</item>
        <item>Bagerhat</item>
        <item>Chuadanga</item>
        <item>Jessore</item>
        <item>Jhenaidah</item>
        <item>Khulna</item>
        <item>Kushtia</item>
        <item>Magura</item>
        <item>Meherpur</item>
        <item>Narail</item>
        <item>Satkhira</item>
    </string-array>*/
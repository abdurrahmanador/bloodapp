package com.example.blood.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blood.R
import com.example.blood.adapters.PostAdapter
import com.example.blood.databinding.ActivityPostDetailsBinding
import com.google.firebase.firestore.*

class PostDetails:AppCompatActivity() {
    private lateinit var binding: ActivityPostDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<com.example.blood.utilities.PostUser>
    private lateinit var db: FirebaseFirestore
    private lateinit var myAdapter: PostAdapter

    val REQUEST_PHONE_CALL=101

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.setTitle("Post Details")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()

        val name:TextView=findViewById(R.id.patientNameTextView)
        val mobile:TextView=findViewById(R.id.patientCNTextView)
        val bloodNeed:TextView=findViewById(R.id.patientBNTextView)
        val bloodGroup:TextView=findViewById(R.id.patientBGTextView)
        val age:TextView=findViewById(R.id.patientAgeTextView)
        val illness:TextView=findViewById(R.id.patientTypeTextView)
        val hospital:TextView=findViewById(R.id.PatientaddressTextView)
        val district:TextView=findViewById(R.id.PatientDistrictTextView)
        val managed:TextView=findViewById(R.id.PatntmanagedET)

        val bundle:Bundle?=intent.extras
        val ptName=bundle!!.getString("name")
        val ptAge=bundle!!.getString("age")
        val ptBG=bundle!!.getString("bloodGroup")
        val ptBN=bundle!!.getString("bloodBag")
        val ptIllness=bundle!!.getString("illness")
        val ptHospital=bundle!!.getString("hospital")
        val ptMobile=bundle!!.getString("mobile")
        val ptDistrict=bundle!!.getString("district")
        val ptmanaged=bundle!!.getString("managed")

        name.text=ptName
        mobile.text=ptMobile
        hospital.text=ptHospital
        district.text=ptDistrict
        age.text=ptAge
        managed.text=ptmanaged
        bloodNeed.text=ptBN
        bloodGroup.text=ptBG
        illness.text=ptIllness
        checkPermissions()
        binding.cardViewCall.setOnClickListener {
            val phoneNumber=ptMobile.toString()
            if(phoneNumber.isNotEmpty()){
                val callIntent=Intent(Intent.ACTION_CALL)
                callIntent.data=Uri.parse("tel:$phoneNumber")
                startActivity(callIntent)
            }
        }

    }
    private fun checkPermissions(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL)
        }
    }


}
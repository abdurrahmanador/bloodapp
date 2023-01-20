package com.example.blood.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.blood.R
import com.example.blood.adapters.MyAdapter
import com.example.blood.databinding.ActivityDonorDetailsBinding
import com.google.firebase.firestore.FirebaseFirestore

class DonorDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDonorDetailsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<com.example.blood.utilities.User>
    private lateinit var db: FirebaseFirestore
    private lateinit var myAdapter: MyAdapter

    val REQUEST_PHONE_CALL = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.title = "Donor Details"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        super.onCreate(savedInstanceState)
        binding = ActivityDonorDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()

        val name: TextView = findViewById(R.id.donorNameTextView)
        val mobile: TextView = findViewById(R.id.donorCNTextView)
        val bloodGroup: TextView = findViewById(R.id.donorBGTextView)
        val availability: TextView = findViewById(R.id.donorAvailabilityTextView)
        val district: TextView = findViewById(R.id.donorDistrictTextView)

        val bundle: Bundle? = intent.extras
        val dnrName = bundle!!.getString("name")
        val dnrAvail = bundle!!.getString("availability")
        val dnrBG = bundle!!.getString("bloodGroup")
        val dnrMobile = bundle!!.getString("mobile")
        val ptDistrict = bundle!!.getString("district")

        name.text = dnrName
        mobile.text =dnrMobile
        district.text = ptDistrict
        availability.text = dnrAvail
        bloodGroup.text = dnrBG
        checkPermissions()
        binding.cardViewCall.setOnClickListener {
            val phoneNumber = dnrMobile.toString()
            if (phoneNumber.isNotEmpty()) {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:$phoneNumber")
                startActivity(callIntent)
            }
        }

    }

    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_PHONE_CALL
            )
        }
    }


}
package com.example.blood.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.R
import com.example.blood.databinding.ActivityUpdatePostBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdatePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdatePostBinding
    private var db = Firebase.firestore
    private lateinit var userID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Update Post"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userID = FirebaseAuth.getInstance().currentUser!!.uid

        val district = resources.getStringArray(R.array.district_name)
        district.sort()
        val districtAdapter = ArrayAdapter(this, R.layout.drop_down_item, district)
        binding.patientDistrictET.setAdapter(districtAdapter)

        val bg = resources.getStringArray(R.array.blood_group)
        district.sort()
        val bgA = ArrayAdapter(this, R.layout.drop_down_item, bg)
        binding.patientbloodGroupET.setAdapter(bgA)

        val bloodManaged = resources.getStringArray(R.array.blood_status)
        val bloodManagedAdapter = ArrayAdapter(this, R.layout.drop_down_item, bloodManaged)
        binding.patientbloodmanageET.setAdapter(bloodManagedAdapter)
        setData()

        binding.updatePostButton.setOnClickListener {
            val uName = binding.patientnameProfileEt.text.toString()
            val uHospital = binding.patientHospitalET.text.toString()
            val uDistrict = binding.patientDistrictET.text.toString()
            val uManaged = binding.patientbloodmanageET.text.toString()
            val uBloodGroup = binding.patientbloodGroupET.text.toString()
            val uContact = binding.patientcontactNumberET.text.toString()

            val updateMap = mapOf(
                "name" to uName,
                "mobile" to uContact,
                "bloodGroup" to uBloodGroup,
                "managed" to uManaged,
                "hospital" to uHospital,
                "district" to uDistrict
            )

            db.collection("patients").document(userID).update(updateMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Please check your Internet connection or\n Post first... ",
                        Toast.LENGTH_SHORT
                    ).show()
                }


        }

    }

    private fun setData() {
        db.collection("patients").document(userID).get().addOnSuccessListener {
            if (it != null) {
                val name = it.data?.get("name")?.toString()
                val district = it.data?.get("district")?.toString()
                val hospital = it.data?.get("hospital")?.toString()
                val mobile = it.data?.get("mobile")?.toString()
                val bloodManaged = it.data?.get("managed")?.toString()
                val bloodGroup = it.data?.get("bloodGroup")?.toString()

                binding.patientnameProfileEt.setText(name)
                binding.patientbloodGroupET.setText(bloodGroup)
                binding.patientbloodmanageET.setText("")
                binding.patientDistrictET.setText("")
                binding.patientcontactNumberET.setText(mobile)
                binding.patientHospitalET.setText(hospital)

            }
        }
            .addOnFailureListener { Toast.makeText(this, "Please check your Internet connection or\n Post first... ", Toast.LENGTH_SHORT).show() }
    }

}
/*
package com.example.blood.activities
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.R
import com.example.blood.databinding.ActivityUpdatePostBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdatePostBinding
    private var db = Firebase.firestore

    private lateinit var userID: String
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Update Post")
/*
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userID=FirebaseAuth.getInstance().currentUser!!.uid

        val district = resources.getStringArray(R.array.district_name)
        district.sort()
        val districtAdapter = ArrayAdapter(this, R.layout.drop_down_item, district)
        binding.patientDistrictET.setAdapter(districtAdapter)
        setData()

        binding.regButtonID.setOnClickListener {
            val uName=binding.patientnameProfileEt.text.toString()
            val uHospital=binding.patientHospitalET.text.toString()
            val uDistrict=binding.patientDistrictET.text.toString()
            val uManaged=binding.patientbloodmanageET.text.toString()
            val uBloodGroup=binding.patientbloodGroupET.text.toString()
            val uContact=binding.patientcontactNumberET.text.toString()

            val updateMap= mapOf(
                "name" to uName,
                "mobile" to uContact,
                "bloodGroup" to uBloodGroup,
                "managed" to uManaged,
                "hospital" to uHospital,
                "district" to uDistrict
            )

            db.collection("patients").document(userID).update(updateMap)
                .addOnSuccessListener {
                    Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                }

        }

    }

    private fun setData(){
        db.collection("patients").document(userID).get().addOnSuccessListener {
            if(it!=null){
                val name=it.data?.get("name")?.toString()
                val district=it.data?.get("district")?.toString()
                val hospital=it.data?.get("hospital")?.toString()
                val mobile=it.data?.get("mobile")?.toString()
                val bloodManaged=it.data?.get("managed")?.toString()
                val bloodGroup=it.data?.get("bloodGroup")?.toString()

                binding.patientnameProfileEt.setText(name)
                binding.patientbloodGroupET.setText(bloodGroup)
                binding.patientbloodmanageET.setText("")
                binding.patientDistrictET.setText("")
                binding.patientcontactNumberET.setText(mobile)
                binding.patientHospitalET.setText(hospital)

            }
        }
            .addOnFailureListener { Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show() }
    }

}
/*/
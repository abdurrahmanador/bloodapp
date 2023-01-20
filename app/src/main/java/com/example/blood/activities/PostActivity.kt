package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.R
import com.example.blood.adapters.PostAdapter
import com.example.blood.databinding.ActivityPostBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PostActivity:AppCompatActivity() {
    private lateinit var binding:com.example.blood.databinding.ActivityPostBinding
    val db=Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Post For Blood")
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        super.onCreate(savedInstanceState)
        binding=ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bloodGroup = resources.getStringArray(R.array.blood_group)
        bloodGroup.sort()
        val arrayAdapter = ArrayAdapter(this, R.layout.drop_down_item, bloodGroup)
        binding.bloodGroupET.setAdapter(arrayAdapter)

        val bloodNeed=resources.getStringArray(R.array.blood_need)
        val bNAdapter=ArrayAdapter(this,R.layout.drop_down_item,bloodNeed)
        binding.BNET.setAdapter(bNAdapter)

        val district=resources.getStringArray(R.array.district_name)
        district.sort()
        val dAdapter=ArrayAdapter(this,R.layout.drop_down_item,district)
        binding.patientDistrictET.setAdapter(dAdapter)

        val managed=resources.getStringArray(R.array.blood_status)
        val mAdapter=ArrayAdapter(this,R.layout.drop_down_item,managed)
        binding.bloodManagedET.setAdapter(mAdapter)

        binding.regButtonID.setOnClickListener {
            postingData()
        }
    }

    private fun postingData() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        val name=binding.nameProfileEt.text?.toString()?.trim()
        val mobile=binding.contactNumberET.text?.toString()?.trim()
        val district=binding.patientDistrictET.text?.toString()?.trim()
        val hospital=binding.patientHospitalET.text?.toString()?.trim()
        val bloodNeed=binding.BNET.text?.toString()?.trim()
        val bloodGroup=binding.bloodGroupET.text?.toString()?.trim()
        val age=binding.ageprofileET.text?.toString()?.trim()
        val illness=binding.illness.text?.toString()?.trim()
        val managed=binding.bloodManagedET.text?.toString()?.trim()

        if(name!!.isNotEmpty() && mobile!!.isNotEmpty() && bloodGroup!!.isNotEmpty() && district!!.isNotEmpty() && hospital!!.isNotEmpty()
            && bloodNeed!!.isNotEmpty() && age!!.isNotEmpty() && illness!!.isNotEmpty() && managed!!.isNotEmpty()) {
            val patient = hashMapOf(
                "name" to name,
                "mobile" to mobile,
                "district" to district,
                "hospital" to hospital,
                "bloodGroup" to bloodGroup,
                "bloodBag" to bloodNeed,
                "age" to age,
                "managed" to managed,
                "illness" to illness
            )
            db.collection("patients").document(userId.toString()).set(patient).addOnSuccessListener {
                    binding.nameProfileEt.text?.clear()
                    binding.contactNumberET.text?.clear()
                    binding.ageprofileET.text?.clear()
                    binding.patientDistrictET.text.clear()
                    binding.patientHospitalET.text?.clear()
                    binding.ageprofileET.text?.clear()
                    binding.illness.text?.clear()
                    binding.bloodGroupET.text.clear()
                    binding.bloodManagedET.text.clear()
                    binding.BNET.text.clear()

                    Toast.makeText(
                        this,
                        "Posted! Please Update Your Data \n if u get blood donor.",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this, NewsfeedActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Please Provide all the informaition\n CORRECTLY.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
        }else{
            Toast.makeText(
                this,
                "Please Provide all the informaition\n CORRECTLY.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
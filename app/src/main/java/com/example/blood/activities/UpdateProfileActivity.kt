package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.R
import com.example.blood.databinding.ActivityUpdateProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdateProfileActivity:AppCompatActivity() {
    private lateinit var binding: ActivityUpdateProfileBinding
    private var db=Firebase.firestore
    private lateinit var userID :String

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.title="Update Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userID=FirebaseAuth.getInstance().currentUser!!.uid

        val availableForDonate = resources.getStringArray(R.array.yes_no)
        val availableAdapter = ArrayAdapter(this, R.layout.drop_down_item, availableForDonate)
        binding.updateAvailableET.setAdapter(availableAdapter)

        val district = resources.getStringArray(R.array.district_name)
        district.sort()
        val districtAdapter = ArrayAdapter(this, R.layout.drop_down_item, district)
        binding.updateDistrictET.setAdapter(districtAdapter)
        setData()

        binding.updateButtonID.setOnClickListener {
            val uName=binding.updateNameProfileEt.text.toString()
            val uEmail=binding.updateEmailProfileEt.text.toString()
            val uDistrict=binding.updateDistrictET.text.toString()
            val uContact=binding.updateContactNumberET.text.toString()
            val uAvailability=binding.updateAvailableET.text.toString()

            val updateMap= mapOf(
                "name" to uName,
                "email" to uEmail,
                "availability" to uAvailability,
                "contactNumber" to uContact,
                "district" to uDistrict
            )

            db.collection("users").document(userID).update(updateMap)
                .addOnSuccessListener {
                    Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(this,"Please check your Internet connection or\n Create a new Profile first... ",Toast.LENGTH_SHORT).show()
                }

        }

    }

    private fun setData(){
        db.collection("users").document(userID).get().addOnSuccessListener {
            if(it!=null){
                val name=it.data?.get("name")?.toString()
                val district=it.data?.get("district")?.toString()
                val availability=it.data?.get("availability")?.toString()
                val contactNumber=it.data?.get("contactNumber")?.toString()
                val email=it.data?.get("email")?.toString()

                binding.updateNameProfileEt.setText(name)
                binding.updateContactNumberET.setText(contactNumber)
                binding.updateEmailProfileEt.setText(email)
                binding.updateAvailableET.setText("")
                binding.updateDistrictET.setText("")

            }
        }
            .addOnFailureListener { Toast.makeText(this,"Please check your Internet connection or\n Create a new Profile first... ",Toast.LENGTH_SHORT).show() }
    }

}
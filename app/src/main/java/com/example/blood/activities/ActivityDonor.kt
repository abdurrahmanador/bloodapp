package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.databinding.ActivityBecomeADonorBinding

class ActivityDonor:AppCompatActivity() {
    private lateinit var binding:ActivityBecomeADonorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Become Donor")

        super.onCreate(savedInstanceState)
        binding= ActivityBecomeADonorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.donorRegister.setOnClickListener{
            val intent=Intent(this,GetSignUpActivity::class.java)
            startActivity(intent)
        }
        binding.updateProfile.setOnClickListener{
            val intent=Intent(this,UpdateProfileActivity::class.java)
            startActivity(intent)
        }
    }

}
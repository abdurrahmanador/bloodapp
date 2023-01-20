package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.databinding.ActivityNeedBloodBinding

class NeedBloodActivity:AppCompatActivity() {
    private lateinit var binding:ActivityNeedBloodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Donor List")

        super.onCreate(savedInstanceState)
        binding=ActivityNeedBloodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Apositive.setOnClickListener {
            val intent=Intent(this, ApositiveActivity::class.java)
            startActivity(intent)
        }
        binding.ANegative.setOnClickListener {
            val intent=Intent(this, ANegativeActivity::class.java)
            startActivity(intent)
        }
        binding.BPositive.setOnClickListener {
            val intent=Intent(this, BPositivieActivity::class.java)
            startActivity(intent)
        }
        binding.BNegative.setOnClickListener {
            val intent=Intent(this,BNegativeActivity::class.java)
            startActivity(intent)
        }
        binding.OPositive.setOnClickListener {
            val intent=Intent(this, OPositiveActivity::class.java)
            startActivity(intent)
        }
        binding.ONegative.setOnClickListener {
            val intent=Intent(this, ONegativeActivity::class.java)
            startActivity(intent)
        }
        binding.ABPositive.setOnClickListener {
            val intent=Intent(this,ABPositiveActivity::class.java)
            startActivity(intent)
        }
        binding.ABNegative.setOnClickListener {
            val intent=Intent(this,ABNegativeActivity::class.java)
            startActivity(intent)
        }
    }
}
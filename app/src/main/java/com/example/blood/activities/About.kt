package com.example.blood.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.databinding.ActivityAboutBinding

class About:AppCompatActivity() {
    private lateinit var binding:ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title="Developer Info"

        super.onCreate(savedInstanceState)
        binding=ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.crd3.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile//arador.arador"))
            startActivity(intent)
        }
        binding.crd4.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com/mail/abiabdullahinshaalloh"))
            startActivity(intent)
        }


    }
}
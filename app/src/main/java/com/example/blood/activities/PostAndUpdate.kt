package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.databinding.ActivityPostUpdateBinding

class PostAndUpdate:AppCompatActivity() {

    private lateinit var binding:ActivityPostUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.setTitle("Create Post")

        super.onCreate(savedInstanceState)
        binding=ActivityPostUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postMake.setOnClickListener {
            startActivity(Intent(this,PostActivity::class.java))
        }
        binding.updatePost.setOnClickListener {
            startActivity(Intent(this,UpdatePostActivity::class.java))
        }

    }


}
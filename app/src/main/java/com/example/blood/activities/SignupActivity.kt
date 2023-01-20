package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.R
import com.example.blood.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    val db = Firebase.firestore
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        //reference

        binding.regButtonID.setOnClickListener {
            val email = binding.emailProfileEt.text.toString()
            val passWord = binding.passET.text.toString()
            val confirmPassword = binding.confirmPassET.text.toString()
            val contact=binding.contactNumberET.text.toString()

            if (email.isNotEmpty() && passWord.isNotEmpty() && confirmPassword.isNotEmpty()&& contact.isNotEmpty()) {
                binding.verificationButton.isEnabled

                firebaseAuth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val verification=firebaseAuth.currentUser?.isEmailVerified
                        if(verification==true){
                            val user=firebaseAuth.currentUser
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                        }
                      else{
                                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                            }

                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please provide Information correctly.", Toast.LENGTH_SHORT)
                    .show()
            }
        }/*
        binding.regButtonID.setOnClickListener {
            val email = binding.emailProfileEt.text.toString().trim()
            val password = binding.passET.text.toString().trim()
            val confirmPass= binding.confirmPassET.text.toString().trim()

            val user = hashMapOf(
                "email" to email,
                "password" to password,
                "confirmPassword" to confirmPass
            )

            db.collection("users")
                .add(user)
                .addOnSuccessListener {
                    binding.confirmPassET.text?.clear()
                    binding.emailProfileEt.text?.clear()
                    binding.passET.text?.clear()
                    Toast.makeText(
                        this, "Congratulation!", Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "PLease Provide all the informaition CORRECTLY.\n" +
                                " or Check Your internet Connection",
                        Toast.LENGTH_SHORT
                    ).show()

                }

        }/**/*/
    }
    }




/*      val bloodGroup = resources.getStringArray(R.array.blood_group)
       val arrayAdapter = ArrayAdapter(this, R.layout.drop_down_item, bloodGroup)
       binding.bloodGroupET.setAdapter(arrayAdapter)

       val availableForDonate = resources.getStringArray(R.array.yes_no)
       val availableAdapter = ArrayAdapter(this, R.layout.drop_down_item, availableForDonate)
       binding.availableET.setAdapter(availableAdapter)

       val cityName=resources.getStringArray(R.array.city_name)
       val cityAdapter=ArrayAdapter(this, R.layout.drop_down_item,cityName)
       binding.cityET.setAdapter(cityAdapter)


       binding.regButtonID.setOnClickListener {
           val name = binding.nameProfileEt.text.toString()
           val email = binding.emailProfileEt.text.toString()
           val phoneNumber = binding.contactNumberET.text.toString()
           val cityName=binding.cityET.text.toString()
           val bloodGroup = binding.bloodGroupET.text.toString()
     //      val availability = binding.availableET.text.toString()

           database = FirebaseDatabase.getInstance().getReference("Users")
           val User = User(name, email, phoneNumber, bloodGroup, cityName/*, availability*/)
           database.child(phoneNumber).setValue(User).addOnSuccessListener {
               binding.nameProfileEt.text?.clear()
               binding.emailProfileEt.text?.clear()
               binding.contactNumberET.text?.clear()
            //   binding.availableET.text.clear()
               binding.bloodGroupET.text.clear()
               binding.cityET.text.clear()

               Toast.makeText(this,"Congratulation! You are" +
                       " a Blood Donor now",Toast.LENGTH_SHORT).show()
           }.addOnFailureListener {

               Toast.makeText(this,"PLease Provide all the informaition\n CORRECTLY.",Toast.LENGTH_SHORT).show()
           }
       }

*/
/*
class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth


   // @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
/*
   */
/*
        firebaseAuth = FirebaseAuth.getInstance()
        binding.regButtonID.setOnClickListener {
            val email = binding.emailProfileEt.text.toString()
            val passWord = binding.passwordETProfile.text.toString()
            val confirmPassword = binding.confirmPasswordETProfile.text.toString()

            if (email.isNotEmpty() && passWord.isNotEmpty() && confirmPassword.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please provide Information correctly.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
*/
    }
}


/*  fun showDatePickerDialog(view: DatePickerFragment) {
      fun showDatePickerDialog(v: View) {
          val newFragment = DatePickerFragment()
          newFragment.show(supportFragmentManager, "datePicker")
      }
  }
}*/
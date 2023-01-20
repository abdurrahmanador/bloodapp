package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.R
import com.example.blood.databinding.ActivityGetSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GetSignUpActivity :AppCompatActivity()
{
    private lateinit var binding: ActivityGetSignUpBinding
    private var db=Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Create Donor Profile")
        binding=ActivityGetSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bloodGroup = resources.getStringArray(R.array.blood_group)
        bloodGroup.sort()
        val arrayAdapter = ArrayAdapter(this, R.layout.drop_down_item, bloodGroup)
        binding.bloodGroupET.setAdapter(arrayAdapter)

        val availableForDonate = resources.getStringArray(R.array.yes_no)
        val availableAdapter = ArrayAdapter(this, R.layout.drop_down_item, availableForDonate)
        binding.availableET.setAdapter(availableAdapter)

        val cityName = resources.getStringArray(R.array.city_name)
        cityName.sort()
        val cityAdapter = ArrayAdapter(this, R.layout.drop_down_item, cityName)
        binding.cityET.setAdapter(cityAdapter)

        val district = resources.getStringArray(R.array.district_name)
        district.sort()
        val districtAdapter = ArrayAdapter(this, R.layout.drop_down_item, district)
        binding.districtET.setAdapter(districtAdapter)

        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        binding.regButtonID.setOnClickListener {
            val name = binding.nameProfileEt.text?.toString()?.trim()
            val availability = binding.availableET.text?.toString()?.trim()
            val city = binding.cityET.text?.toString()?.trim()
            val bloodGroup = binding.bloodGroupET.text?.toString()?.trim()
            val district = binding.districtET.text?.toString()?.trim()
            val contactNumber = binding.contactNumberET.text?.toString()?.trim()
            val email = binding.emailProfileEt.text?.toString()?.trim()

            val user= hashMapOf(
                "name" to name,
                "city" to city,
                "contactNumber" to contactNumber,
                "bloodGroup" to bloodGroup,
                "availability" to availability,
                "email" to email,
                "district" to district
            )
            db.collection("users").document(userId.toString()).set(user).addOnSuccessListener {

                if (name!!.isNotEmpty() && email!!.isNotEmpty() && bloodGroup!!.isNotEmpty() && district!!.isNotEmpty() && availability!!.isNotEmpty()) {
                    binding.nameProfileEt.text?.clear()
                    binding.emailProfileEt.text?.clear()
                    binding.availableET.text.clear()
                    binding.bloodGroupET.text.clear()
                    binding.cityET.text.clear()
                    binding.districtET.text.clear()
                    binding.contactNumberET.text?.clear()
                    Toast.makeText(this, "Successfully Registered.\n Please Update your" +
                            " 'Availability' status after donation.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)

                }
                else{
                    Toast.makeText(
                        this,
                        "PLease Provide all the informaition CORRECTLY.\n" + " or Check Your internet Connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                Toast.makeText(
                    this,
                    "PLease Provide all the informaition CORRECTLY.\n" + " or Check Your internet Connection",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }


    }

}
/*
class GetSignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetSignUpBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Create Donor Profile")

        super.onCreate(savedInstanceState)

        binding = ActivityGetSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bloodGroup = resources.getStringArray(R.array.blood_group)
        bloodGroup.sort()
        val arrayAdapter = ArrayAdapter(this, R.layout.drop_down_item, bloodGroup)
        binding.bloodGroupET.setAdapter(arrayAdapter)

        val availableForDonate = resources.getStringArray(R.array.yes_no)
        val availableAdapter = ArrayAdapter(this, R.layout.drop_down_item, availableForDonate)
        binding.availableET.setAdapter(availableAdapter)

        val cityName = resources.getStringArray(R.array.city_name)
        cityName.sort()
        val cityAdapter = ArrayAdapter(this, R.layout.drop_down_item, cityName)
        binding.cityET.setAdapter(cityAdapter)

        val district = resources.getStringArray(R.array.district_name)
        district.sort()
        val districtAdapter = ArrayAdapter(this, R.layout.drop_down_item, district)
        binding.districtET.setAdapter(districtAdapter)

        binding.regButtonID.setOnClickListener {
            val name = binding.nameProfileEt.text?.toString()?.trim()
            val availability = binding.availableET.text?.toString()?.trim()
            val city = binding.cityET.text?.toString()?.trim()
            val bloodGroup = binding.bloodGroupET.text?.toString()?.trim()
            val district = binding.districtET.text?.toString()?.trim()
            val contactNumber = binding.contactNumberET.text?.toString()?.trim()
            val email = binding.emailProfileEt.text?.toString()?.trim()

            val user = hashMapOf(
                "name" to name,
                "city" to city,
                "contactNumber" to contactNumber,
                "bloodGroup" to bloodGroup,
                "availability" to availability,
                "email" to email,
                "district" to district

            )

            val userId = FirebaseAuth.getInstance()

            db.collection("USERS").document(userId.toString()).set(user).addOnSuccessListener {


                    if (name!!.isNotEmpty() && email!!.isNotEmpty() && bloodGroup!!.isNotEmpty() && district!!.isNotEmpty() && availability!!.isNotEmpty()) {
                        binding.nameProfileEt.text?.clear()
                        binding.emailProfileEt.text?.clear()
                        binding.availableET.text.clear()
                        binding.bloodGroupET.text.clear()
                        binding.cityET.text.clear()
                        binding.districtET.text.clear()
                        binding.contactNumberET.text?.clear()
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    }
                else{
                        Toast.makeText(
                            this,
                            "PLease Provide all the informaition CORRECTLY.\n" + " or Check Your internet Connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(
                        this,
                        "PLease Provide all the informaition CORRECTLY.\n" + " or Check Your internet Connection",
                        Toast.LENGTH_SHORT
                    ).show()

                }

        }
    }
}

/*

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
}
/*
    private lateinit var binding: ActivityGetSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        super.onCreate(savedInstanceState)
        binding = ActivityGetSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupButton.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val passWord = binding.passET.text.toString()
            val confirmPassword = binding.confirmPassET.text.toString()

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

}*/

 */*/
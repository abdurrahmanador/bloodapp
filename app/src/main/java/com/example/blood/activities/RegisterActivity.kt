package com.example.blood.activities

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.databinding.ActivityRegisterBinding
import com.google.firebase.database.DatabaseReference

class RegisterActivity:AppCompatActivity() {

    }
/*
    var day=0
    var month=0
    var year=0
    var saveDate=0
    var saveMonth=0
    var saveYear=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(R.layout.fragment_profile)
        setContentView(binding.root)
        pickDate()

        binding.saveProfileInfo.setOnClickListener {
            val name=binding.regName.text.toString()
            val phone=binding.dpPhone.text.toString()
            val city=binding.dpCity.text.toString()
            val age=binding.dpAge.text.toString()
            val date=binding.donateDate.text.toString()
            val email=binding.dpEmail.text.toString()

            database= FirebaseDatabase.getInstance().getReference("User")
            val User=Constants(name,phone,city,age,date,email)
            database.child(phone).setValue(User).addOnSuccessListener {
                binding.regName.text?.clear()
                binding.dpCity.text?.clear()
                binding.dpAge.text?.clear()
                binding.dpPhone.text?.clear()
                binding.dpEmail.text?.clear()
                Toast.makeText(this,"Successfully Profile Uploaded",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed!",Toast.LENGTH_SHORT).show()
            }
        }

    }    private fun pickDate() {
        binding.donateDate.setOnClickListener {
            getDateCalender()
            DatePickerDialog(this,this,year,month,day).show()
        }
    }
    private fun getDateCalender()
    {
        val c= Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)
    }
    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        saveDate=dayOfMonth
        saveMonth=month
        saveYear=year
        getDateCalender()
        binding.donateDate.text="$saveDate-$saveMonth-$saveYear"
    }
*/


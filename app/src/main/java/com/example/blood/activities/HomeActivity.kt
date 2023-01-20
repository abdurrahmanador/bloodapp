package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.blood.R
import com.example.blood.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var backPressedTime: Long = 0

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawarLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> Toast.makeText(
                    applicationContext,
                    "You Are already here",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_login -> Toast.makeText(
                    applicationContext,
                    "You Are already Logged In",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_logout -> {
                    firebaseAuth.signOut()
                    val intent = Intent(this, SigninActivity::class.java)
                    startActivity(intent)

                }
                R.id.nav_about ->
                    startActivity(Intent(this,About::class.java))

                R.id.nav_share -> {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "type/palin"
                    val shareBody = "You are body"
                    val shareSub = "You subject here"
                    intent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
                    intent.putExtra(Intent.EXTRA_TEXT, shareSub)
                    startActivity(Intent.createChooser(intent, "Share your App"))
                }
            }
            true
        }


        binding.becomeDonorHome.setOnClickListener {
            val intent = Intent(this, ActivityDonor::class.java)
            startActivity(intent)
        }
        binding.newsFeedHome.setOnClickListener {
            val intent = Intent(this, NewsfeedActivity::class.java)
            startActivity(intent)
        }
        binding.needAmbulanceHome.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.needBloodHome.setOnClickListener {
            val intent = Intent(this, NeedBloodActivity::class.java)
            startActivity(intent)
        }
        binding.createPost.setOnClickListener {
            val intent = Intent(this, PostAndUpdate::class.java)
            startActivity(intent)
        }
        binding.logOut.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
      /*  binding.createPostgrid.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
        }
        binding.updatePostBelow.setOnClickListener {
            startActivity(Intent(this, UpdatePostActivity::class.java))
        }
        binding.createProfileBelow.setOnClickListener {
            val intent = Intent(this, GetSignUpActivity::class.java)
            startActivity(intent)
        }
        binding.updateProfileBelow.setOnClickListener {
            val intent = Intent(this, UpdateProfileActivityprevious::class.java)
            startActivity(intent)
        }*/
        binding.needAmbulanceHome.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "type/palin"
            val shareBody = "You are body"
            val shareSub = "You subject here"
            intent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
            intent.putExtra(Intent.EXTRA_TEXT, shareSub)
            startActivity(Intent.createChooser(intent, "Share your App"))
        }


    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity()
        } else {
            Toast.makeText(this, "Tap again to exit.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

//
//    override fun onBackPressed() {
//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
//            backToast?.cancel()
//            super.onBackPressed()
//            return
//        }
//        else{
//            backToast =Toast.makeText(this,"Press again to EXIT app",Toast.LENGTH_SHORT)
//            backToast?.show()
//        }
//        backPressedTime=System.currentTimeMillis()
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


/*
    replaceFragment(HomeFragment())
    binding.bottomNavigationView.setOnItemSelectedListener {
        when (it.itemId) {
            R.id.home -> replaceFragment(HomeFragment())
            R.id.search -> replaceFragment(SearchFragment())
            R.id.profile -> replaceFragment(ProfileFragment())
            else -> {

            }
        }
        true
    }


}

private fun replaceFragment(fragment: Fragment) {
    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.frameLayout, fragment)
    fragmentTransaction.commit()
}

fun switchToDonorFragment(){
    val donorFragment=NotificationFragment()
    supportFragmentManager.beginTransaction().add( R.id.frameLayout,donorFragment)
        .commit()
}*/


/*        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()*/

//xml
/*
*   <TextView
        android:id="@+id/homeTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="80dp"
        android:fontFamily="sans-serif-black"
        android:gravity="center"
        android:text="Home"
        android:background="@color/"
        android:textColor="#F20A1D"
        android:textSize="50sp" />*/
/*
*     <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="bottom"
        android:layout_marginStart="150dp"
        android:layout_below="@id/gr1"
        android:text="Experimental Version 1.0.0"
        android:textColor="@color/black"
        android:textSize="12sp" />*/
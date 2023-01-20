package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blood.databinding.FragmentTourBinding

class TourActivity:AppCompatActivity() {
    private lateinit var binding:FragmentTourBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentTourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivnot.alpha=0f
        binding.ivnot.animate().setDuration(1600).alpha(1f).withEndAction{
            startActivity(Intent(this,SigninActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
        binding.textView2.animate().setDuration(1600).alpha(1f).withEndAction{
            startActivity(Intent(this,SigninActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
        supportActionBar?.hide()
    }

  /*  private fun fragmentTransition(fragment: Fragment){
        var fragmentManager=supportFragmentManager
        var fragmentTransition=fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frameLayout,fragment)
        fragmentTransition.commit()
    }*/
}
/*
*
<FrameLayout
android:id="@+id/frameLayout"
android:layout_width="0dp"
android:layout_height="0dp"
app:layout_constraintBottom_toTopOf="@id/bottomNavigationView"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent" />
<com.google.android.material.bottomnavigation.BottomNavigationView
android:id="@+id/bottomNavigationView"
android:layout_width="0dp"
app:circularflow_radiusInDP="20"
android:layout_height="wrap_content"
android:elevation="2dp"
android:background="@color/cardview_shadow_start_color"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintHorizontal_bias="0.5"
app:menu="@menu/nav_menu"
app:labelVisibilityMode="unlabeled"
app:layout_constraintStart_toStartOf="parent" />

*/
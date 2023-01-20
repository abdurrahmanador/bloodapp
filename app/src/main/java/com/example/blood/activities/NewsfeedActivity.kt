package com.example.blood.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blood.R
import com.example.blood.adapters.MyAdapter
import com.example.blood.adapters.PostAdapter
import com.example.blood.databinding.ActivityHomeBinding
import com.example.blood.databinding.ActivityNewsfeedBinding
import com.example.blood.utilities.PostUser
import com.example.blood.utilities.User
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import java.util.*
import kotlin.collections.ArrayList

class NewsfeedActivity:AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<com.example.blood.utilities.PostUser>
    private lateinit var details: ArrayList<com.example.blood.utilities.PostUser>
    private lateinit var db: FirebaseFirestore
    private lateinit var myAdapter: PostAdapter
    private lateinit var searchView: SearchView
    private lateinit var binding:ActivityNewsfeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.title="News feed"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        binding = ActivityNewsfeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.userList)
        searchView = findViewById(R.id.searchView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        userList = arrayListOf()
        details = arrayListOf()

        myAdapter = PostAdapter(userList)
        recyclerView.adapter = myAdapter

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("NewsFeed")

        Post()

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchList(newText)
                return true
            }

        })
    }
    fun searchList(text:String){
        val searchList=kotlin.collections.ArrayList<PostUser>()
        for(i in userList){
            if(i.district?.lowercase()?.contains(text.lowercase())==true){
                searchList.add(i)
            }
        }
        myAdapter.searchDataList(searchList)
    }
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                filterList(newText)
//                return true
//            }
//
//        })
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setTitle("NewsFeed")
//
//    }
//
//    private fun filterList(query: String?) {
//        if(query!=null){
//            val filteredList=ArrayList<PostUser>()
//            for(i in userList){
//                if(i.district?.lowercase(Locale.ROOT)!!.contains(query)){
//                    filteredList.add(i)
//                }
//            }
//            if(filteredList.isEmpty()){
//
//            }else{
//                myAdapter.setFilteredList(filteredList)
//            }
//        }
//    }

    /*
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("NewsFeed")

    }

    private fun filterList(query: String?) {
        if(query!=null){
            val filteredList=ArrayList<PostUser>()
            for(i in userList){
                if(i.district?.lowercase(Locale.ROOT)!!.contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){

            }else{
                myAdapter.setFilteredList(filteredList)
            }
        }
    }
*/
    private fun Post() {
        db.collection("patients").whereEqualTo("managed","Finding Blood Donor")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?) {
                    if(error!=null){
                        Log.e("Firestore Error",error.message.toString())
                        return
                    }
                    for (db : DocumentChange in value?.documentChanges!!){
                        if(db.type== DocumentChange.Type.ADDED){
                            userList.add(db.document.toObject(com.example.blood.utilities.PostUser::class.java))
                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }

            })
        myAdapter.setOnItemClickListener(object :PostAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent= Intent(this@NewsfeedActivity,PostDetails::class.java)
                intent.putExtra("name",userList[position].name)
                intent.putExtra("mobile",userList[position].mobile)
                intent.putExtra("illness",userList[position].illness)
                intent.putExtra("hospital",userList[position].address)
                intent.putExtra("district",userList[position].district)
                intent.putExtra("bloodGroup",userList[position].bloodGroup)
                intent.putExtra("bloodBag",userList[position].bloodBag)
                intent.putExtra("age",userList[position].age)
                intent.putExtra("managed",userList[position].managed)
                startActivity(intent)
            }
        })
    }
}



/*
class NewsfeedActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var postUser:kotlin.collections.ArrayList<PostUser>
    private lateinit var db: FirebaseFirestore
    private lateinit var postAdapter:PostAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newsfeed)
        db = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.userList)
        searchView = findViewById(R.id.searchView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        postUser = arrayListOf()

        postAdapter = PostAdapter(postUser)
        recyclerView.adapter = postAdapter

    }

// private lateinit var binding:ActivityNewsfeedBinding

//
//
// override fun onCreate(savedInstanceState: Bundle?) {
// super.onCreate(savedInstanceState)
// binding= ActivityNewsfeedBinding.inflate(layoutInflater)
// setContentView(binding.root)
/*

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("Users")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(User::class.java)
                        userList.add(user!!)
                    }
                    recyclerView.adapter=MyAdapter(userList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                finish()
            }

        })
*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        val item=menu?.findItem(R.id.search)
        val searchView=item?.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tempArrayList.clear()
                val searchText=newText!!.toLowerCase(Locale.getDefault())
                if(searchText.isNotEmpty()){

                    userList.forEach {
                        if(it.phoneNumber?.toLowerCase(Locale.getDefault())!!.contains(searchText)){
                            tempArrayList.add(it)
                        }
                    }

                    recyclerView.adapter!!.notifyDataSetChanged()

                }else{
                    tempArrayList.clear()
                    tempArrayList.addAll(userList)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }*/
// }

/*
        db.collection("users").get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for (data in it.documents){
                        val user:User?=data.toObject<User>(User::class.java)
                        userList.add(user!!)
                    }
                    recyclerView.adapter=MyAdapter(userList)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
            }*/

/*
        db = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.userList)
        searchView=findViewById(R.id.searchView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        userList = arrayListOf()
        myAdapter = MyAdapter(userList)
        recyclerView.adapter = myAdapter
        EventChangeListener()
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query: String?) {
        if(query!=null){
            val filteredList=ArrayList<User>()
            for (i in userList){
                if(i.city!!.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){
            }else{
                myAdapter.setFilteredList(filteredList)
            }
        }
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("users").orderBy("bloodGroup", Query.Direction.ASCENDING)
            .addSnapshotListener(object : com.google.firebase.firestore.EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            userList.add(dc.document.toObject(User::class.java))

                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }
            }
            )
    }
* */
//------------------------------
/*
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
        post()

    }

    private fun filterList(query: String?) {
        if(query!=null){
            val filteredList=ArrayList<PostUser>()
            for(i in postUser){
                if(i.district?.lowercase(Locale.ROOT)!!.contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){

            }else{
                postAdapter.setFilteredList(filteredList)
            }
        }
    }

//    private fun post() {
//        db.collection("patients")
//            .addSnapshotListener(object :EventListener<QuerySnapshot>{
//                override fun onEvent(
//                    value: QuerySnapshot?,
//                    error: FirebaseFirestoreException?) {
//                    if(error!=null){
//                        Log.e("ERROR", error.message.toString())
//                        return
//                    }
//                    for(db:DocumentChange in value?.documentChanges!!){
//                        if(db.type==DocumentChange.Type.ADDED){
//                            postUser.add(db.document.toObject(PostUser::class.java))
//                        }
//                    }
//                    postAdapter.notifyDataSetChanged()
//                }
//
//            })
//
//    }
private fun post() {
    db.collection("patients")
        .addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.e("Firestore Error",error.message.toString())
                    return
                }
                for (db : DocumentChange in value?.documentChanges!!){
                    if(db.type== DocumentChange.Type.ADDED){
                        postUser.add(db.document.toObject(com.example.blood.utilities.PostUser::class.java))
                    }
                }
                postAdapter.notifyDataSetChanged()
            }

        })

}
}*/*/
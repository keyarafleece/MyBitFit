package com.example.mybitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybitfit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    // private variable to inflate the layout for the activity
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    // variable to access the ViewModel class
    val viewModel : ContactViewModel  by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.nav_view)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)


       /* val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)
*/
        // inflate the layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set onClickListener for the floating action button
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this , CreateContact::class.java)
            startActivity(intent)
        }

        // Observe the LiveData returned by the getAllContacts method
        viewModel.getAllContacts().observe(this , Observer {  list->
            // set the layout manager and the adapter for the recycler view
            binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            binding.recyclerView.adapter = ContactsAdapter(this,list)
        })
    }
}
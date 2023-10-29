package com.example.notetaking.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.notetaking.R

class HomeActivity : AppCompatActivity() {
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(findViewById(R.id.toolbarHome))

        navController = findNavController(R.id.nav_home)
        navController?.let { navController -> setupActionBarWithNavController(navController) }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() == true || super.onSupportNavigateUp()
    }
}
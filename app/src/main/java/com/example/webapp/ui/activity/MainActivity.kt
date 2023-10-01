package com.example.webapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.webapp.R
import com.example.webapp.databinding.ActivityMainBinding
import com.example.webapp.ui.fragment.CustomDialog_add_data

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddPost.setOnClickListener { CustomDialog_add_data().show(supportFragmentManager,"") }

        setUpNav()
    }

    private fun setUpNav() {
        var navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        var navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.btnNavMain,navController)
    }
}
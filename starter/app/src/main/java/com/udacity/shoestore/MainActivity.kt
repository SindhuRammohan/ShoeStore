package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration


    lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
//        val navController = navHostFragment.navController


        setSupportActionBar(binding.toolbar)
        drawerLayout = binding.drawerLayout
        navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)


        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onNavigateUp(): Boolean {
        val navController = this.findNavController((R.id.myNavHostFragment))
        return navController.navigateUp()
    }
}
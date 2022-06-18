package com.example.aroundegypttask.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.example.aroundegypttask.R
import com.example.aroundegypttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide();
        setUpNavController()
    }

    private fun setUpNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(onDestinationChangedListener)
    }

    private val onDestinationChangedListener =
        OnDestinationChangedListener { _: NavController?, destination: NavDestination, _: Bundle? ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.searchView.visibility = View.VISIBLE
                    binding.sideMenuIv.visibility = View.VISIBLE
                    binding.toolbarIv.visibility = View.VISIBLE
                    binding.mainToolbar.setBackgroundColor(resources.getColor(R.color.white))
                }
                else -> {
                    binding.mainToolbar.visibility = View.GONE
                    binding.sideMenuIv.visibility = View.GONE
                    binding.toolbarIv.visibility = View.GONE
                    binding.searchView.visibility = View.GONE
                }
            }
        }
}
package com.aliozdemir.asparagas

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.aliozdemir.asparagas.databinding.ActivityMainBinding
import com.aliozdemir.asparagas.util.applySystemInsetsMargin
import com.aliozdemir.asparagas.util.applySystemInsetsPadding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        binding.fragmentContainerView.applySystemInsetsPadding(applyBottom = false)

        binding.bottomNavigationView.applySystemInsetsMargin()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigationView.visibility = when (destination.id) {
                R.id.detailFragment -> View.GONE
                else -> View.VISIBLE
            }
        }
    }
}

package com.sue.coroutine_searchimage.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.sue.coroutine_searchimage.R
import com.sue.coroutine_searchimage.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    private val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initViews() = with(binding) {
        setSupportActionBar(toolBar)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.searchImageFragment, R.id.favoriteImageFragment))

        setupActionBarWithNavController(navigationController, appBarConfiguration)
        bottomNavView.setupWithNavController(navigationController)

        navigationController.addOnDestinationChangedListener { _, destination, argument ->
            when (destination.id) {

            }
        }
    }
}
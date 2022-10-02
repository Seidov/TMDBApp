package com.sultanseidov.tmdbapp.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sultanseidov.tmdbapp.R
import com.sultanseidov.tmdbapp.databinding.ActivityMainBinding
import com.sultanseidov.tmdbapp.view.fragment.DiscoverFragment
import com.sultanseidov.tmdbapp.view.fragment.MyWatchListFragment
import com.sultanseidov.tmdbapp.view.fragment.SearchFragment
import com.sultanseidov.tmdbapp.view.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

private lateinit var binding: ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListener()
        loadFragment(DiscoverFragment())
    }


    private fun setupClickListener() {
        binding.bottomNav.setOnItemSelectedListener {

            val fragment = when (it.itemId) {
                R.id.nv_mywatchlist -> {
                    MyWatchListFragment()
                }
                R.id.nv_search -> {
                    SearchFragment()
                }
                else -> {
                    DiscoverFragment()
                }
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_container, fragment)
            .commit()
    }



}
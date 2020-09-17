package com.masscode.moviejetpack.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.masscode.moviejetpack.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navCtrl: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        supportActionBar?.elevation = 0f

        navCtrl = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_nav, navCtrl)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navCtrl.navigateUp()
    }
}
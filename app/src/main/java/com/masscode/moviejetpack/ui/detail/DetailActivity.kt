package com.masscode.moviejetpack.ui.detail

import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.masscode.moviejetpack.R
import com.masscode.moviejetpack.databinding.ActivityDetailBinding

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    companion object {
        const val ID = "id"
        const val TYPE = "type"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.lifecycleOwner = this

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getInt(ID)
            val type = extras.getString(TYPE)

            Log.d("ID", "id: $id")
            Log.d("type", "type: $type")

            if (type == "movie") {
                viewModel.setSelectedItem(id)
                viewModel.getDetailMovie()
            } else {
                viewModel.setSelectedItem(id)
                viewModel.getDetailTvShow()
            }
        }

        binding.viewModel = viewModel
    }

    private fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val window = activity.window
        val winParams = window.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        window.attributes = winParams
    }
}
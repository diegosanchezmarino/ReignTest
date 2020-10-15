package com.dsm.reigntestapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.reigntestapp.R
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_left)
            .replace(R.id.container, ArticlesFragment::class.java, null)
            .commit()
    }
}
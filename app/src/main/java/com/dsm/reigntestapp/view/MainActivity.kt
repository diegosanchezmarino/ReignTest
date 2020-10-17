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
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right)
            .add(R.id.container, PostsFragment::class.java, null)
            .commit()





    }
}
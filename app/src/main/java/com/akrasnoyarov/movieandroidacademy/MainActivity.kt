package com.akrasnoyarov.movieandroidacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akrasnoyarov.movieandroidacademy.fragments.MovieDetailsFragment

class MainActivity : SingleFragmentActivity() {
    override fun createFragment() = MovieDetailsFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, createFragment())
                .commit()
        }
    }

}
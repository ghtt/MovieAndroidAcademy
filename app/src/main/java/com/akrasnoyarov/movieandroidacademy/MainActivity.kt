package com.akrasnoyarov.movieandroidacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akrasnoyarov.movieandroidacademy.fragments.MovieDetailsFragment

class MainActivity : SingleFragmentActivity() {
    override fun createFragment() = MovieDetailsFragment.newInstance()

}
package dev.enhance.exercise.core.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dev.enhance.exercise.R
import dev.enhance.exercise.spacex.SpaceXFragment
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportFragmentManager.beginTransaction().replace(R.id.flContent, SpaceXFragment()).commit()
    }
}
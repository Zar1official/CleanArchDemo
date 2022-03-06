package ru.zar1official.cleanarchdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ru.zar1official.cleanarchdemo.R
import ru.zar1official.cleanarchdemo.databinding.ActivityMainBinding
import ru.zar1official.cleanarchdemo.ui.screens.list.CharactersListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            supportFragmentManager.commit {
                replace(R.id.fragment_container, CharactersListFragment.newInstance())
            }
        }
    }
}
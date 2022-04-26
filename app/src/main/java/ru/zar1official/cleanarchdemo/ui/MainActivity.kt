package ru.zar1official.cleanarchdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import ru.zar1official.cleanarchdemo.R
import ru.zar1official.cleanarchdemo.databinding.ActivityMainBinding
import ru.zar1official.cleanarchdemo.ui.screens.Screens
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            router.navigateTo(Screens.ListOfCharactersScreen)
        }
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(
            AppNavigator(
                this,
                containerId = R.id.fragment_container
            )
        )
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }
}
package com.mahmoudroid.marvelcharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mahmoudroid.marvelcharacters.repository.CharactersRepository
import com.mahmoudroid.marvelcharacters.ui.CharactersViewModelProviderFactory
import com.mahmoudroid.marvelcharacters.ui.CharactersViewModel

class CharactersActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    lateinit var viewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        val charactersRepository = CharactersRepository()
        val viewModelProviderFactory = CharactersViewModelProviderFactory(charactersRepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(CharactersViewModel::class.java)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.charactersNavHostFragment)
                    as NavHostFragment

        navController = navHostFragment.navController
    }
}

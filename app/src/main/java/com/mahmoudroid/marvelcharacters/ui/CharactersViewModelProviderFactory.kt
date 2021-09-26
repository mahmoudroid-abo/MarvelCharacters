package com.mahmoudroid.marvelcharacters.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahmoudroid.marvelcharacters.repository.CharactersRepository

class CharactersViewModelProviderFactory(
    private val charactersRepository: CharactersRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersViewModel(charactersRepository) as T
    }
}
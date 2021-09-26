package com.mahmoudroid.marvelcharacters.repository

import com.mahmoudroid.marvelcharacters.api.RetrofitInstance

class CharactersRepository() {

    //page number
    suspend fun getMarvelCharacters() =
        RetrofitInstance.charactersApi.getCharacters()

    suspend fun searchMarvelCharacters(searchQuery: String) {
        RetrofitInstance.charactersApi.searchCharacters(searchQuery)
    }
}
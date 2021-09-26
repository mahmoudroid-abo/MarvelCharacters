package com.mahmoudroid.marvelcharacters.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudroid.marvelcharacters.models.Character
import com.mahmoudroid.marvelcharacters.models.Result
import com.mahmoudroid.marvelcharacters.repository.CharactersRepository
import com.mahmoudroid.marvelcharacters.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel(
    val charactersRepository: CharactersRepository
) : ViewModel() {

    val TAG = "CharactersViewModel"

    // character instead of Result
    val charactersLiveData: MutableLiveData<Resource<Character>> = MutableLiveData()
    var charactersPage = 0
    var charactersResponse: Character? = null

    val searchCharactersLiveData: MutableLiveData<Resource<Result>> = MutableLiveData()
    val searchPage = 1
    var characterSearchResponse: Character? = null

    init {
        getCharactersList()
    }

    fun getCharactersList() = viewModelScope.launch {
        charactersLiveData.postValue(Resource.Loading())

        val response = charactersRepository.getMarvelCharacters()

        charactersLiveData.postValue(handleCharactersResponse(response))

    }

    //Error Type Mismatch
//    fun searchCharacter(searchQuery: String) = viewModelScope.launch {
//        searchCharactersLiveData.postValue(Resource.Loading())
//        val response = charactersRepository.searchMarvelCharacters(searchQuery)
//        searchCharactersLiveData.postValue(handleSearchCharactersResponse(response))
//    }


    private fun handleCharactersResponse(response: Response<Character>)
            : Resource<Character> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
//                charactersPage++
//                if (charactersResponse == null) {
//                    charactersResponse = resultResponse
//                } else {
//                    val oldCharacters = charactersResponse?.
//                }
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }

    private fun handleSearchCharactersResponse(response: Response<Result>)
            : Resource<Result> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }

        return Resource.Error(response.message())
    }

}
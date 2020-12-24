package com.aripratom.gamecatalogue.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aripratom.core.domain.usecase.GameUseCase

class SearchViewModel (private val gameUseCase: GameUseCase) : ViewModel() {

    private val searchQuery = MutableLiveData("")

    val searchGames = Transformations.switchMap(searchQuery) {
        gameUseCase.searchGames(it).asLiveData()
    }

    fun setSearchQuery(name: String) {
        searchQuery.value = name
    }
}
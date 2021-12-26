package mobi.mele.beers.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobi.mele.beers.common.Event
import mobi.mele.domain.dto.Beer
import mobi.mele.usecases.FindBeersByNameUseCase
import mobi.mele.usecases.GetBeersUseCase

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
class MainViewModel(
    private val getBeersUseCase: GetBeersUseCase,
    private val findBeersByNameUseCase: FindBeersByNameUseCase
): ViewModel() {

    sealed class UIModelBeers {
        object Loading : UIModelBeers()
        class Content(val beers: List<Beer>) : UIModelBeers()
    }

    private val _navigation = MutableLiveData<Event<Beer>>()
    val navigation: LiveData<Event<Beer>> = _navigation

    private val _uiModelBeers= MutableLiveData<UIModelBeers>()
    val uiModelBeers: LiveData<UIModelBeers>
        get() {
            if (_uiModelBeers.value == null) refresh()
            return _uiModelBeers
        }

    /*
    * Carga en el recycler a traves del adaptador una lista de cervezas
    */
    fun refresh() {
        viewModelScope.launch {
            _uiModelBeers.value = UIModelBeers.Loading
            _uiModelBeers.value = UIModelBeers.Content(getBeersUseCase.invoke())
        }
    }

    /*
    * Detecta la cerveza elegida de la lista
    */
    fun onRecipeClicked(beer: Beer) {
        _navigation.value = Event(beer)
    }

    /*
    * Realiza una búsqueda de cervezas que contenga la query en la propiedad beer_name en el servidor
    */
    fun doSearch(query: String) {
        viewModelScope.launch {
            _uiModelBeers.value = UIModelBeers.Content(findBeersByNameUseCase.invoke(query))
        }
    }
}
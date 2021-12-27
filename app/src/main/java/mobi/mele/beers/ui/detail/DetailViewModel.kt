package mobi.mele.beers.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobi.mele.beers.parcelizeobject.BeerParcelize
import mobi.mele.domain.dto.Beer
import mobi.mele.usecases.FindBeerByIdUseCase

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
class DetailViewModel(
    private val beerId: Int,
    private val findBeerByIdUseCase: FindBeerByIdUseCase
    ) : ViewModel() {

    data class UiModelBeer(val beer: List<Beer>)

    private val _uiModelBeer = MutableLiveData<UiModelBeer>()
    val uiModelBeer: LiveData<UiModelBeer>
        get() {
            if (_uiModelBeer.value == null) findBeer()
            return _uiModelBeer
        }

    private fun findBeer() {
        viewModelScope.launch {
            _uiModelBeer.value = UiModelBeer(findBeerByIdUseCase.invoke(beerId))
        }

    }
}
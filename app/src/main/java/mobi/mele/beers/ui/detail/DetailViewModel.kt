package mobi.mele.beers.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mobi.mele.beers.parcelizeobject.BeerParcelize

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
class DetailViewModel(
    val beer: BeerParcelize?
    ) : ViewModel() {

    data class UiModelBeer(val beer: BeerParcelize?)

    private val _uiModelBeer = MutableLiveData<UiModelBeer>()
    val uiModelBeer: LiveData<UiModelBeer>
        get() {
            if (_uiModelBeer.value == null) getBeer()
            return _uiModelBeer
        }

    private fun getBeer() {
        _uiModelBeer.value = UiModelBeer(beer)
    }
}
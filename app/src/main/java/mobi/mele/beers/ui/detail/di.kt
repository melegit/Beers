package mobi.mele.beers.ui.detail

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import mobi.mele.beers.parcelizeobject.BeerParcelize

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */

@Module
class DetailModule(private val beer: BeerParcelize?) {

    @Provides
    fun detailViewModelProvider() = DetailViewModel(beer)

}

@Subcomponent(modules = [(DetailModule::class)])
interface DetailComponent {
    val detailViewModel: DetailViewModel
}
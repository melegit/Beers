package mobi.mele.beers.ui.detail

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import mobi.mele.beers.parcelizeobject.BeerParcelize
import mobi.mele.data.repository.BeersRepository
import mobi.mele.usecases.FindBeerByIdUseCase

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */

@Module
class DetailModule(private val beerId: Int) {

    @Provides
    fun detailViewModelProvider(
        findBeerByIdUseCase: FindBeerByIdUseCase,
    ) = DetailViewModel(beerId, findBeerByIdUseCase)

    @Provides
    fun findBeerByIdUseCaseProvider(beersRepository: BeersRepository) =
        FindBeerByIdUseCase(beersRepository)

}

@Subcomponent(modules = [(DetailModule::class)])
interface DetailComponent {
    val detailViewModel: DetailViewModel
}
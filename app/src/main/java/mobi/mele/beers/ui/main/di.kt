package mobi.mele.beers.ui.main

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import mobi.mele.data.repository.BeersRepository
import mobi.mele.usecases.FindBeersByNameUseCase
import mobi.mele.usecases.GetBeersUseCase

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
@Module
class MainModule {

    @Provides
    fun mainViewModelProvider(
        getBeersUseCase: GetBeersUseCase,
        findBeersByNameUseCase: FindBeersByNameUseCase
    ) = MainViewModel(getBeersUseCase, findBeersByNameUseCase)

    @Provides
    fun getBeersUseCaseProvider(
        beersRepository: BeersRepository
    ) = GetBeersUseCase(beersRepository)

    @Provides
    fun findBeersByNameUseCaseProvider(
        beersRepository: BeersRepository
    ) = FindBeersByNameUseCase(beersRepository)
}

@Subcomponent(modules = [(MainModule::class)])
interface MainComponent {
    val mainViewModel: MainViewModel
}
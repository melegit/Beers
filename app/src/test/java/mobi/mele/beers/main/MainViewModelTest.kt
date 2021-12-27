package mobi.mele.beers.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import mobi.mele.beers.MainCoroutineScopeRule
import mobi.mele.beers.ui.main.MainViewModel
import mobi.mele.beers.ui.main.MainViewModel.UIModelBeers
import mobi.mele.domain.dto.Beer
import mobi.mele.testshared.mockedBeer
import mobi.mele.usecases.FindBeersByNameUseCase
import mobi.mele.usecases.GetBeersUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/**
 * Created by Antonio Fernández
 * date   : 27/12/21
 * e-mail : meleappdev@gmail.com
 */
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTesT {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getBeersUseCase: GetBeersUseCase

    @Mock
    lateinit var findBeersByNameUseCase: FindBeersByNameUseCase

    @Mock
    lateinit var observer: Observer<UIModelBeers>

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(getBeersUseCase, findBeersByNameUseCase)
    }

    @Test
    fun `before getBeers, loading is shown`() {
        coroutineScope.runBlockingTest{

            val beers = listOf<Beer>(mockedBeer.copy(1))
            whenever(getBeersUseCase.invoke()).thenReturn(beers)
            viewModel.uiModelBeers.observeForever(observer)

            viewModel.refresh()

            verify(observer).onChanged(UIModelBeers.Loading)
        }
    }
}

package mobi.mele.beers.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import mobi.mele.beers.MainCoroutineScopeRule
import mobi.mele.beers.ui.detail.DetailViewModel
import mobi.mele.beers.ui.detail.DetailViewModel.*
import mobi.mele.beers.ui.main.MainViewModel
import mobi.mele.domain.dto.Beer
import mobi.mele.testshared.mockedBeer
import mobi.mele.usecases.FindBeerByIdUseCase
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
class DetailViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var findBeerByIdUseCase: FindBeerByIdUseCase

    @Mock
    lateinit var observer: Observer<UiModelBeer>

    private lateinit var detailViewModel: DetailViewModel
    private var id = 5

    @Before
    fun setUp() {
        detailViewModel =
            DetailViewModel(id, findBeerByIdUseCase)
    }

    @Test
    fun `observing LiveData finds the beer`() =

        coroutineScope.runBlockingTest {

            //GIVEN
            val beer = listOf<Beer>(mockedBeer.copy(5))
            whenever(findBeerByIdUseCase.invoke(5)).thenReturn(beer)

            //WHEN
            detailViewModel.uiModelBeer.observeForever(observer)

            //THEN
            verify(observer).onChanged(UiModelBeer.Content(beer))
        }

    @Test
    fun `before getBeer, loading is shown`() {
        coroutineScope.runBlockingTest{

            val beer = listOf<Beer>(mockedBeer.copy(1))
            whenever(findBeerByIdUseCase.invoke(5)).thenReturn(beer)

            detailViewModel.uiModelBeer.observeForever(observer)

            detailViewModel.findBeer()

            verify(observer).onChanged(UiModelBeer.Loading)
        }
    }
}
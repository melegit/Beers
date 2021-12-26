package mobi.mele.usecases

import mobi.mele.data.repository.BeersRepository
import mobi.mele.domain.dto.Beer

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
class FindBeersByNameUseCase(private val beersRepository: BeersRepository) {
    suspend fun invoke(nameBeers: String) : List<Beer> = beersRepository.findBeersByName(nameBeers)
}
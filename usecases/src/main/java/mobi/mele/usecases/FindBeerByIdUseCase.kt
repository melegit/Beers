package mobi.mele.usecases

import mobi.mele.data.repository.BeersRepository
import mobi.mele.domain.dto.Beer

/**
 * Created by Antonio Fernández
 * date   : 27/12/21
 * e-mail : meleappdev@gmail.com
 */
class FindBeerByIdUseCase(private val beersRepository: BeersRepository) {
    suspend fun invoke(id: Int): List<Beer> = beersRepository.findBeerById(id)
}
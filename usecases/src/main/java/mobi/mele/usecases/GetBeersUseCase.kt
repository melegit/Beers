package mobi.mele.usecases

import mobi.mele.data.repository.BeersRepository
import mobi.mele.domain.dto.Beer

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
class GetBeersUseCase(private val beersRepository: BeersRepository) {
    suspend fun invoke(): List<Beer> = beersRepository.getBeers()
}
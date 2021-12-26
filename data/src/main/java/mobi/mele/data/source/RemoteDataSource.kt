package mobi.mele.data.source

import mobi.mele.domain.dto.Beer

/**
 * Created by Antonio Fernández
 * date   : 25/12/21
 * e-mail : meleappdev@gmail.com
 */
interface RemoteDataSource {
    suspend fun getBeers(): List<Beer>
    suspend fun findBeersByName(nameBeers : String): List<Beer>
}
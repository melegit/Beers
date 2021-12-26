package mobi.mele.beers.server

import mobi.mele.data.source.RemoteDataSource
import mobi.mele.domain.dto.Beer

/**
 * Created by Antonio Fernández
 * date   : 26/12/21
 * e-mail : meleappdev@gmail.com
 */
class ServerDataSource : RemoteDataSource {
    override suspend fun getBeers(): List<Beer> =
        ApiPunk.service.getBeers()

    override suspend fun findBeersByName(nameBeers: String): List<Beer> =
        ApiPunk.service.findBeersByName(nameBeers)
}
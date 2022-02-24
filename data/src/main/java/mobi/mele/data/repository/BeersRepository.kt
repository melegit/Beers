package mobi.mele.data.repository

import mobi.mele.data.source.RemoteDataSource
import mobi.mele.domain.dto.Beer

/**
 * Created by Antonio Fernández
 * date   : 25/12/21
 * e-mail : meleappdev@gmail.com
 */
class BeersRepository(private val remoteDataSource: RemoteDataSource) {

    /**
     * devuelve una lista de cervezas
     */
    suspend fun getBeers(): List<Beer> = remoteDataSource.getBeers()

    /**
     * devuelve una lista de cervezas que contengan en el nombre la cadena nameBeers
     */
    suspend fun findBeersByName(nameBeers: String): List<Beer> = remoteDataSource.findBeersByName(nameBeers)

    /**
     * devuelve en una lista de único objeto la receta.
     */
    suspend fun findBeerById(id: Int): List<Beer> = remoteDataSource.findBeerById(id)
}
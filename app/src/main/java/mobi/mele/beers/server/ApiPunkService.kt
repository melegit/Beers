package mobi.mele.beers.server

import mobi.mele.domain.dto.Beer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Antonio Fernández
 * date   : 25/12/21
 * e-mail : meleappdev@gmail.com
 */
interface ApiPunkService {

    ////////////////// B E E R S //////////////////
    @GET("beers")
    suspend fun getBeers(): List<Beer>

    @GET("beers")
    suspend fun findBeersByName(@Query("beer_name")nameBeers : String): List<Beer>

    @GET("beers")
    suspend fun findBeerById(@Query("ids")id : String): List<Beer>
}